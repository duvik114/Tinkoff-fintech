package ru.tinkoff.fintech.plain.servlet.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.tinkoff.fintech.plain.servlet.demo.model.Student;

import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentController extends HttpServlet {

    private final ObjectMapper jackson;

    public StudentController(ObjectMapper jackson) {
        this.jackson = jackson;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        resp
            .getWriter()
            .print(jackson.writeValueAsString(Student.of("Andy", 20)));
    }
}

