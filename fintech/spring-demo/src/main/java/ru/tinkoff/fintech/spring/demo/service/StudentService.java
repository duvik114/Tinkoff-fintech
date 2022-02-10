package ru.tinkoff.fintech.spring.demo.service;

import org.springframework.stereotype.Component;
import ru.tinkoff.fintech.spring.demo.model.Student;

@Component
public class StudentService {

    public Student create(String name, Integer age) {
        assert !name.isBlank();
        assert age > 0;
        return Student.of(name, age);
    }
}
