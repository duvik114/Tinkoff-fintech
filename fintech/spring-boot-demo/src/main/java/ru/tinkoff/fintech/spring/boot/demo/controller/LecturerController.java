package ru.tinkoff.fintech.spring.boot.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.tinkoff.fintech.spring.boot.demo.service.LecturerService;

@RestController
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
