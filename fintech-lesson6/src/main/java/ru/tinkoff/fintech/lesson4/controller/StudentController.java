package ru.tinkoff.fintech.lesson4.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import ru.tinkoff.fintech.lesson4.model.Student;
import ru.tinkoff.fintech.lesson4.model.StudentsToCourseMapper;
import ru.tinkoff.fintech.lesson4.service.CourseService;
import ru.tinkoff.fintech.lesson4.service.StudentService;
import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;

    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @PatchMapping(
            consumes = APPLICATION_JSON_VALUE,
            path = "/students/add"
    )
    @Operation(
            description = "Students to Course mapper",
            summary = "Map students to one course"
    )
    public void addStudentsToCourse(@RequestBody @Valid StudentsToCourseMapper mapper) {
        for (var sid : mapper.getStudentIdList()) {
            if (studentService.find(sid).getGrade() < courseService.find(mapper
                    .getCourseId()).getRequiredGrade()) {
                throw new IllegalArgumentException();
            }
        }
        studentService.addStudentsToCourse(mapper);
    }

    @GetMapping(
            consumes = APPLICATION_JSON_VALUE,
            path = "/students/"
    )
    @Operation(
            description = "GET student by id",
            summary = "GET student by id"
    )
    public Student getStudentById(@RequestParam("uuid") UUID uuid) {
        return studentService.find(uuid);
    }

    @PostMapping(
            consumes = APPLICATION_JSON_VALUE,
            path = "/students/"
    )
    @Operation(
            description = "Save student by id",
            summary = "Save student by id"
    )
    public void saveStudent(@RequestBody @Valid Student student) {
        studentService.save(student);
    }

    @PutMapping(
            consumes = APPLICATION_JSON_VALUE,
            path = "/students/"
    )
    @Operation(
            description = "Update student by id",
            summary = "Update student by id"
    )
    public void updateStudent(@RequestBody @Valid Student student) {
        studentService.update(student);
    }

    @DeleteMapping(
            consumes = APPLICATION_JSON_VALUE,
            path = "/students/"
    )
    @Operation(
            description = "Delete student by id",
            summary = "Delete student by id"
    )
    public void deleteStudentById(@RequestParam("uuid") UUID uuid) {
        studentService.delete(uuid);
    }
}
