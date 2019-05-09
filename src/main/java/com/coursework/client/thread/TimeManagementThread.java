package com.coursework.client.thread;

import com.coursework.client.service.GeneratorService;
import com.coursework.client.service.TimeManagementService;

import java.util.Date;

public class TimeManagementThread extends Thread {

    private static Long period;
    private static Date currentDate;

    private static TimeManagementService timeManagementService = TimeManagementService.getInstance();

    @Override
    public void run() {
        currentDate = new Date();
        while (true) {
            timeManagement();
        }
    }

    private static void timeManagement() {
        if (period == null) {
            period = (long) (GeneratorService.generate(120, 180) * 10000);
            Thread.yield();
        }
        if ((currentDate.getTime() + period) < System.currentTimeMillis()) {
            period = (long) (GeneratorService.generate(120, 180) * 10000);
            currentDate = new Date();

            timeManagementService.run();
        }
    }
}
