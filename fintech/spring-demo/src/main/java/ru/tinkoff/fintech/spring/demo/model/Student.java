package ru.tinkoff.fintech.spring.demo.model;

import java.util.UUID;

public record Student(UUID id, String name, Integer age) {

    public static Student of(String name, Integer age) {
        return new Student(UUID.randomUUID(), name, age);
    }
}
