package ru.tinkoff.fintech.lesson4.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private UUID id;
    private String name;
    private int age;
    private Course course;
}
