package com.coursework.client.service;

public class GeneratorService {
    private static Integer X0 = 10000;
    private static final Integer A = 5;
    private static final Integer C = 6;
    private static final Integer D = 7;

    public static Integer generate(Integer n, Integer shift) {
        Integer result = (A * X0 * X0 + C * X0 + D) % n + shift;
        X0 = result;
        return result;
    }
}
