package com.coursework.client;

import com.coursework.client.entity.User;
import com.coursework.client.service.GeneratorService;
import com.coursework.client.service.JSONService;
import com.coursework.client.service.TimeManagementService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Server {

    private static ServerSocket server;
    private static Socket client;
    private static int PORT = 8008;
    private static BufferedReader reader;
    private static BufferedWriter writer;

    private static JSONService jsonService = JSONService.getInstance();
    private static TimeManagementService timeManagementService = TimeManagementService.getInstance();

    private static Long period;
    private static Date currentDate;

    public static void main(String[] args) {
        try {
            try {
                server = new ServerSocket(PORT);
                printToConsole("The client is running!");
                printToConsole("Waiting to send a schedule from the server ...");
                currentDate = new Date();
                try {
                    while (true) {
                        client = server.accept();

                        reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                        writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

                        String line;
                        StringBuilder stringBuilder = new StringBuilder();
                        while (!(line = reader.readLine()).equals("stop")) {
                            stringBuilder.append(line);
                        }

                        if (!stringBuilder.toString().isEmpty()) {
                            printToConsole("The schedule has been received");
                            printToConsole("The schedule is processing ...");

                            writer.write("success\n");
                            writer.flush();
                            client = null;

                            setDataToTimeManagement(stringBuilder.toString());
                            printToConsole("The schedule transfer completed");
                            //while (true) {
                            timeManagement();
                            //}
                        }
                    }
                } finally {
                    if (client != null) {
                        client.close();
                    }
                    reader.close();
                    writer.close();
                }
            } finally {
                printToConsole("Close connection");
                if (server != null) {
                    server.close();
                }
            }
        } catch (IOException e) {
            printToConsole("Connection error: " + e.getMessage());
        }
    }

    private static void printToConsole(String newValue) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy '-' HH:mm:ss ': '");
        String date = dateFormat.format(new Date());
        System.out.println(date + newValue);
    }

    private static void setDataToTimeManagement(String json) {
        User user = jsonService.deserializeUser(json);
        timeManagementService.setUser(user);
        timeManagementService.run();
    }

    private static void timeManagement() {
        if (period == null) {
            period = (long) (GeneratorService.generate(120, 180) * 100);
            try {
                Thread.sleep(period);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if ((currentDate.getTime() + period) < System.currentTimeMillis()) {
            period = (long) (GeneratorService.generate(120, 180) * 100);
            currentDate = new Date();

            if (timeManagementService != null) {
                timeManagementService.run();
            }

            try {
                Thread.sleep(period);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
