package com.coursework.client.service;

import com.coursework.client.entity.Schedule;
import com.coursework.client.entity.Segment;
import com.coursework.client.entity.TimeSpan;
import com.coursework.client.entity.User;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
        this.user = user;
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

                    if (timeSpan != null) {
                        List<Segment> segments = timeSpan.getSegments();

                        if (segments != null) {
                            int j = 0;
                            Segment segment = segments.get(j);
                            while (j < segments.size()
                                    && compareTime(currentDate.getTime(),
                                    segment.getStart().getTime(),
                                    segment.getEnd().getTime()) > 0) {
                                if (j + 1 < segments.size()) {
                                    segment = segments.get(j + 1);
                                } else {
                                    segment = null;
                                }
                                j++;
                            }

                            if (segment != null) {
                                action(segment);
                            } else {
                                action(null);
                            }
                        }
                    } else {
                        action(null);
                        TimeSpan oldTimeSpan = timeSpans.get(timeSpans.size() - 1);
                        oldTimeSpan.setDate(new Date(oldTimeSpan.getDate().getTime() + 1000 * 60 * 60 * 24));
                        user.getSchedule().getTimeSpans().add(oldTimeSpan);
                    }
                }
            }
        }
    }

    private int compareTime(long currentTime, long segmentTimeStart, long segmentTimeEnd) {
        if (currentTime >= segmentTimeEnd) {
            return 1;
        } else {
            if (currentTime >= segmentTimeStart - 1000) {
                return 0;
            } else {
                return -1;
            }
        }
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
            if (segment == null && internet) {
                Runtime.getRuntime().exec("netsh wlan disconnect");
                printToConsole("The Internet has disabled");
                internet = false;
            }
            if (segment != null && !internet) {
                Runtime.getRuntime().exec("netsh wlan connect name=iPhone");
                printToConsole("The Internet has enabled");
                internet = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printToConsole(String newValue) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy '-' HH:mm:ss ': '");
        String date = dateFormat.format(new Date());
        System.out.println(date + newValue);
    }
}
