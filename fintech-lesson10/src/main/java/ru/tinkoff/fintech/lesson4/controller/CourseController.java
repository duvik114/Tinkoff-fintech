package ru.tinkoff.fintech.lesson4.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.fintech.lesson4.model.Course;
import ru.tinkoff.fintech.lesson4.service.CourseService;
import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class CourseController {

    private final CourseService courseService;

    public CourseController (CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(
            consumes = APPLICATION_JSON_VALUE,
            path = "/courses/"
    )
    @Operation(
            description = "GET Course by id",
            summary = "GET Course by id"
    )
    public Course getCourseById(@RequestParam("id") int id) {
        return courseService.find(id);
    }

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            path = "/courses/"
    )
    @Operation(
            description = "Save Course by id",
            summary = "Save Course by id"
    )
    public void setCourse(@RequestBody @Valid Course course) {
        courseService.save(course);
    }

    @PutMapping(
            consumes = APPLICATION_JSON_VALUE,
            path = "/courses/"
    )
    @Operation(
            description = "Update Course by id",
            summary = "Update Course by id"
    )
    public void updateCourse(@RequestBody @Valid Course course) {
        courseService.update(course);
    }

    @DeleteMapping(
            consumes = APPLICATION_JSON_VALUE,
            path = "/courses/"
    )
    @Operation(
            description = "Delete Course by id",
            summary = "Delete Course by id"
    )
    public void deleteCourseById(@RequestParam("id") int id) {
        courseService.delete(id);
    }
}
