package ru.tinkoff.fintech.spring.demo.service;

import org.springframework.stereotype.Component;
import ru.tinkoff.fintech.spring.demo.model.Lecturer;

@Component
public class LecturerService {

    public Lecturer create(String name, Integer age) {
        assert !name.isBlank();
        assert age > 0;
        return Lecturer.of(name, age);
    }
}
