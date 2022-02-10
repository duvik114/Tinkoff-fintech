package ru.tinkoff.fintech.spring.boot.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.tinkoff.fintech.spring.boot.demo.service.StudentService;

@Component
public class StudentController {

    private final ObjectMapper jackson;
    private final StudentService studentService;

    private final String defaultName;

    public StudentController(ObjectMapper jackson, StudentService studentService, @Value("${student.default-name}") String defaultName) {
        this.jackson = jackson;
        this.studentService = studentService;
        this.defaultName = defaultName;
    }

    @GetMapping(
        value = "/student",
        produces = "application/json")
    @ResponseBody
    public String createStudent() throws JsonProcessingException {
        return jackson.writeValueAsString(studentService.create(defaultName, 20));
    }
}
