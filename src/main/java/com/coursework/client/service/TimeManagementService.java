package com.coursework.client.service;

import com.coursework.client.entity.Schedule;
import com.coursework.client.entity.Segment;
import com.coursework.client.entity.TimeSpan;
import com.coursework.client.entity.User;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TimeManagementService {
    private User user;
    private static TimeManagementService instance;

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
                    while (i < timeSpans.size() && currentDate.compareTo(timeSpan.getDate()) > 0) {
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
                                    && compareTime(currentDate.getTime(), segment.getStart().getTime()) > 0) {
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

    private int compareTime(long currentTime, long segmentTime) {
        if (currentTime <= segmentTime + 1000) {
            if (currentTime >= segmentTime - 1000) {
                return 0;
            } else {
                return -1;
            }
        } else {
            return 1;
        }
    }

    private void action(Segment segment) {
        try {
            if (segment == null) {
                Runtime.getRuntime().exec("netsh wlan disconnect");
                printToConsole("The Internet has disabled");
            } else {
                Runtime.getRuntime().exec("netsh wlan connect name=iPhone");
                printToConsole("The Internet has enabled");
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
