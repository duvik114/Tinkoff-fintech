package ru.tinkoff.fintech.plain.servlet.demo.model;

import java.util.UUID;

public record Lecturer(UUID id, String name, Integer age) {

    public static Lecturer of(String name, Integer age) {
        return new Lecturer(UUID.randomUUID(), name, age);
    }
}
