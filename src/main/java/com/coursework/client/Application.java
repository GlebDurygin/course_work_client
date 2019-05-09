package com.coursework.client;

import com.coursework.client.entity.Schedule;
import com.coursework.client.entity.Segment;
import com.coursework.client.entity.TimeSpan;
import com.coursework.client.entity.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Application {
    /*
    public static void main(String[] args) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            String str = "{\"_entityName\":\"coursework_Segment\",\"id\":\"b86c343d-695d-1ba4-423b-f04bb51ac49b\",\"start\":\"08:09:52\",\"version\":1,\"duration\":8000,\"end\":\"08:10:00\"}";

            String string = "{\"_entityName\":\"coursework_User\",\"id\":\"85fee1d1-9952-30eb-1d20-3cca990965af\",\"lastName\":\"Р”СѓСЂС‹РіРёРЅ\",\"schedule\":{\"_entityName\":\"coursework_Schedule\",\"id\":\"f1dbd63f-864f-5366-599f-dd2097e63223\",\"timeSpans\":[{\"_entityName\":\"coursework_TimeSpan\",\"id\":\"717a5932-8f1b-10c7-aeda-4a14f7eabbeb\",\"date\":\"2019-05-06\",\"schedule\":{\"_entityName\":\"coursework_Schedule\",\"id\":\"f1dbd63f-864f-5366-599f-dd2097e63223\"},\"version\":1,\"segments\":[{\"_entityName\":\"coursework_Segment\",\"id\":\"b86c343d-695d-1ba4-423b-f04bb51ac49b\",\"start\":\"08:09:52\",\"version\":1,\"duration\":8000,\"end\":\"08:10:00\",\"timeSpan\":{\"_entityName\":\"coursework_TimeSpan\",\"id\":\"717a5932-8f1b-10c7-aeda-4a14f7eabbeb\"}},{\"_entityName\":\"coursework_Segment\",\"id\":\"a92d53b6-a6fc-3ea4-1562-75a5335c53fd\",\"start\":\"08:05:01\",\"version\":1,\"duration\":291000,\"end\":\"08:09:52\",\"timeSpan\":{\"_entityName\":\"coursework_TimeSpan\",\"id\":\"717a5932-8f1b-10c7-aeda-4a14f7eabbeb\"}},{\"_entityName\":\"coursework_Segment\",\"id\":\"e1dc9f3e-2303-ac27-3341-c023d10ac286\",\"start\":\"08:00:00\",\"version\":1,\"duration\":267000,\"end\":\"08:04:27\",\"timeSpan\":{\"_entityName\":\"coursework_TimeSpan\",\"id\":\"717a5932-8f1b-10c7-aeda-4a14f7eabbeb\"}}]}],\"version\":1},\"ip\":\"192.168.0.7\",\"name\":\"Р“Р»РµР±\",\"version\":1}";

            User user = mapper.readValue(string, User.class);
            Segment segment = mapper.readValue(str, Segment.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Segment segment = new Segment("432423", "id1", new Time(100), 1, 134554L, new Time(200));

        List<Segment> segments = new ArrayList<>();
        segments.add(segment);
        TimeSpan timeSpan = new TimeSpan("id2", new Date(), segments, "432423", "2");

        List<TimeSpan> timeSpans = new ArrayList<>();
        timeSpans.add(timeSpan);
        Schedule schedule = new Schedule("id3", timeSpans, "432423", "3");

        User user = new User("id4", "Gleb", "Durygin", "765436", schedule, "432423", "4");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String strSegment = objectMapper.writeValueAsString(segment);
            String strSegment1 = objectMapper.writeValueAsString(timeSpan);
            String strSegment2 = objectMapper.writeValueAsString(schedule);
            String strSegment3 = objectMapper.writeValueAsString(user);
            System.out.println(strSegment);
            System.out.println(strSegment1);
            System.out.println(strSegment2);
            System.out.println(strSegment3);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }*/
}
