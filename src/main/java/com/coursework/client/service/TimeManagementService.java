package com.coursework.client.service;

import com.coursework.client.entity.Schedule;
import com.coursework.client.entity.Segment;
import com.coursework.client.entity.TimeSpan;
import com.coursework.client.entity.User;

import java.io.IOException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TimeManagementService {
    private User user;
    private static TimeManagementService instance;
    private boolean internet;

    private TimeManagementService() {
    }

    public static synchronized TimeManagementService getInstance() {
        if (instance == null) {
            instance = new TimeManagementService();
        }
        return instance;
    }

    public void setUser(User user) {
        this.user = sortUserByTime(user);
    }

    private User sortUserByTime(User user) {
        List<TimeSpan> sortTimeSpans = user.getSchedule()
                .getTimeSpans()
                .stream()
                .sorted(Comparator.comparing(TimeSpan::getDate))
                .collect(Collectors.toList());

        for (TimeSpan timeSpan : sortTimeSpans) {
            timeSpan.setSegments(timeSpan.getSegments()
                    .stream()
                    .sorted(Comparator.comparing(Segment::getStart))
                    .collect(Collectors.toList()));
        }

        user.getSchedule().setTimeSpans(sortTimeSpans);
        return user;
    }

    public void run() {
        if (user != null) {
            Schedule schedule = user.getSchedule();
            if (schedule != null) {
                Date currentDate = new Date();
                List<TimeSpan> timeSpans = schedule.getTimeSpans();

                if (timeSpans != null) {
                    int i = 0;
                    TimeSpan timeSpan = timeSpans.get(i);
                    while (i < timeSpans.size() && compareDate(currentDate, timeSpan.getDate()) > 0) {
                        if (i + 1 < timeSpans.size()) {
                            timeSpan = timeSpans.get(i + 1);
                        } else {
                            timeSpan = null;
                        }
                        i++;
                    }

                    if (timeSpan != null
                            && compareDate(currentDate, timeSpan.getDate()) == 0) {
                        List<Segment> segments = timeSpan.getSegments();

                        if (segments != null) {
                            int j = 0;
                            Segment segment = segments.get(j);
                            while (j < segments.size()
                                    && compareTime(currentDate,
                                    segment.getStart(),
                                    segment.getEnd()) > 0) {
                                if (j + 1 < segments.size()) {
                                    segment = segments.get(j + 1);
                                } else {
                                    segment = null;
                                }
                                j++;
                            }

                            if (segment != null && compareTime(currentDate, segment.getStart(), segment.getEnd()) == 0) {
                                action(segment);
                            } else {
                                action(null);
                            }
                        } else {
                            action(null);
                        }
                    } else {
                        action(null);
                        if (timeSpan == null) {
                            TimeSpan oldTimeSpan = timeSpans.get(timeSpans.size() - 1);
                            oldTimeSpan.setDate(new Date(oldTimeSpan.getDate().getTime() + 1000 * 60 * 60 * 24));
                            user.getSchedule().getTimeSpans().add(oldTimeSpan);
                        }
                    }
                }
            }
        }
    }

    private int compareTime(Date currentDateTime, Time segmentTimeStart, Time segmentTimeEnd) {
        Calendar calendarCurrent = Calendar.getInstance();
        calendarCurrent.setTime(currentDateTime);

        Calendar calendarTimeSpanStart = Calendar.getInstance();
        calendarTimeSpanStart.setTime(segmentTimeStart);

        calendarCurrent.set(Calendar.YEAR, calendarTimeSpanStart.get(Calendar.YEAR));
        calendarCurrent.set(Calendar.MONTH, calendarTimeSpanStart.get(Calendar.MONTH));
        calendarCurrent.set(Calendar.DAY_OF_MONTH, calendarTimeSpanStart.get(Calendar.DAY_OF_MONTH));

        Time currentTime = new Time(calendarCurrent.getTime().getTime());
        return currentTime.compareTo(segmentTimeStart) > -1
                && currentTime.compareTo(segmentTimeEnd) < 1
                ? 0
                : currentTime.compareTo(segmentTimeStart) < 0
                ? -1
                : 1;
    }

    private int compareDate(Date currentDate, Date timeSpanDate) {
        Calendar calendarCurrent = Calendar.getInstance();
        Calendar calendarTimeSpan = Calendar.getInstance();
        calendarCurrent.setTime(currentDate);
        calendarTimeSpan.setTime(timeSpanDate);
        if (calendarCurrent.get(Calendar.DAY_OF_YEAR) == calendarTimeSpan.get(Calendar.DAY_OF_YEAR)
                && calendarCurrent.get(Calendar.YEAR) == calendarTimeSpan.get(Calendar.YEAR)) {
            return 0;
        } else if (calendarCurrent.get(Calendar.DAY_OF_YEAR) < calendarTimeSpan.get(Calendar.DAY_OF_YEAR)
                || calendarCurrent.get(Calendar.YEAR) < calendarTimeSpan.get(Calendar.YEAR)) {
            return -1;
        } else {
            return 1;
        }
    }

    private void action(Segment segment) {
        try {
            if (segment == null) {
                Runtime.getRuntime().exec("netsh wlan disconnect");
                printToConsole("The Internet has disabled");
                internet = false;
            } else if (!internet) {
                Runtime.getRuntime().exec("netsh wlan connect name=iPhone");
                printToConsole("The Internet has enabled");
                internet = true;
            }
        } catch (IOException e) {
            System.out.println("Не удалось подключить/отключить доступ к сети Интренет. Ошибка на клиенте.");
            System.out.println("Увеличьте права доступа и повторите еще раз.");
        }
    }

    private static void printToConsole(String newValue) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy '-' HH:mm:ss ': '");
        String date = dateFormat.format(new Date());
        System.out.println(date + newValue);
    }
}
