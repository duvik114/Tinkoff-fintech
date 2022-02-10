package ru.tinkoff.fintech.spring.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.tinkoff.fintech.spring.demo.service.LecturerService;

@Controller
public class LecturerController {

    private final ObjectMapper jackson;
    private final LecturerService lecturerService;

    private final String defaultName;

    public LecturerController(ObjectMapper jackson, LecturerService lecturerService, @Value("${lecturer.default-name}") String defaultName) {
        this.jackson = jackson;
        this.lecturerService = lecturerService;
        this.defaultName = defaultName;
    }

    @GetMapping(
        value = "/lecturer",
        produces = "application/json")
    @ResponseBody
    public String createLecturer() throws JsonProcessingException {
        return jackson.writeValueAsString(lecturerService.create(defaultName, 35));
    }
}
