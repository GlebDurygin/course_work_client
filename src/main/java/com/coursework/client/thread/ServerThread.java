package com.coursework.client.thread;

public class ServerThread extends Thread {
    /*
    private static ServerSocket server;
    private static Socket client;
    private static int PORT = 8008;
    private static BufferedReader reader;
    private static BufferedWriter writer;

    private static JSONService jsonService = JSONService.getInstance();
    private static TimeManagementService timeManagementService = TimeManagementService.getInstance();
    //private static Thread backgroundThread;

    @Override
    public void run() {
        try {
            try {
                server = new ServerSocket(PORT);
                printToConsole("The client is running!");
                printToConsole("Waiting to send a schedule from the server ...");
                //currentDate = new Date();
                //startTimeManagementThread();
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

                            setDataToTimeManagement(stringBuilder.toString());

                            writer.write("success\n");
                            writer.flush();
                            client = null;
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
        /*if (backgroundThread == null || timeManagementService == null) {
            timeManagementService = new TimeManagementService<>(user);
            backgroundThread = new Thread(timeManagementService);
            backgroundThread.setDaemon(true);
            backgroundThread.run();
        } else {
            timeManagementService.setUser(user);
        }
        timeManagementService.setUser(user);
        timeManagementService.run();
    }*/
}
