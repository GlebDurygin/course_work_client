package com.coursework.client;

import com.coursework.client.service.GeneratorService;
import com.coursework.client.service.TimeManagementService;
import com.coursework.client.thread.ServerThread;
import com.coursework.client.thread.TimeManagementThread;

import java.util.Date;


public class Example {/*
    private static Long period;
    private static Date currentDate;

    //private static ServerThread<Void> serverTask;
    private static TimeManagementService timeManagementService = TimeManagementService.getInstance();


    public static void main(String[] args) {
        TimeManagementThread timeManagementThread = new TimeManagementThread();
        timeManagementThread.setPriority(Thread.NORM_PRIORITY);
        //timeManagementThread.setDaemon(true);

        ServerThread serverThread = new ServerThread();
        serverThread.setPriority(Thread.MAX_PRIORITY);
        //serverThread.setDaemon(true);

        timeManagementThread.run();
        serverThread.run();


        //currentDate = new Date();
        //while (true) {
        //    timeManagement();
        //}
    }

    private static void timeManagement() {
        if (period == null) {
            period = (long) (GeneratorService.generate(120, 180) * 10);
        }
        if ((currentDate.getTime() + period) < System.currentTimeMillis()) {
            period = (long) (GeneratorService.generate(120, 180) * 10);
            currentDate = new Date();

            timeManagementService.run();
        }
    }
    */
}
