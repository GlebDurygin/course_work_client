package com.coursework.client.service;

import com.coursework.client.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JSONService {
    private static JSONService instance;

    private JSONService() {
    }

    public static synchronized JSONService getInstance() {
        if (instance == null) {
            instance = new JSONService();
        }
        return instance;
    }

    public User deserializeUser(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String jsonUser = json.substring(2, json.length() - 1).replaceAll(" ", "");
            return objectMapper.readValue(jsonUser, User.class);
        } catch (IOException e) {
            System.out.println("Ошибка при десериализации расписания. Ошибка на сервере.");
            System.out.println("Сделайте расписание еще раз");
        }
        return null;
    }
}
