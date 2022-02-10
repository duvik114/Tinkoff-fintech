package ru.tinkoff.fintech.lesson4.controller.studentTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.tinkoff.fintech.lesson4.Lesson4Application;
import ru.tinkoff.fintech.lesson4.model.Course;
import ru.tinkoff.fintech.lesson4.model.Student;
import ru.tinkoff.fintech.lesson4.model.StudentsToCourseMapper;
import ru.tinkoff.fintech.lesson4.service.CourseService;
import ru.tinkoff.fintech.lesson4.service.StudentService;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Lesson4Application.class)
@AutoConfigureMockMvc
public class StudentDBOperationsTest {

    private final ObjectMapper jackson = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    @BeforeEach
    void setUp() {
        for (Student student : studentService.findAll()) {
            studentService.delete(student.getId());
        }

        for (Course course : courseService.findAll()) {
            courseService.delete(course.getId());
        }
        courseService.clearCache();
    }

    @Test
    void setStudentSuccessTest() throws Exception {
        Course course = typicalCourse();
        courseService.save(course);

        Student student = typicalStudent(UUID.randomUUID());
        var studentJson = jackson.writeValueAsString(student);
        mockMvc.perform(post("/students/")
                        .contentType("application/json")
                        .content(studentJson))
                .andExpect(status().isOk());
        Student student2 = studentService.find(student.getId());
        assertEquals(student, student2);
    }

    @Test
    void getStudentSuccessTest() throws Exception {
        Course course = typicalCourse();
        courseService.save(course);

        Student student = typicalStudent(UUID.randomUUID());
        studentService.save(student);

        mockMvc.perform(get("/students/")
                        .contentType("application/json")
                        .param("uuid", student.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(content().string(jackson.writeValueAsString(student)));
    }

    @Test
    void updateStudentSuccessTest() throws Exception {
        Course course = typicalCourse();
        courseService.save(course);

        Student student = typicalStudent(UUID.randomUUID());
        studentService.save(student);
        Student student2 = typicalStudent(student.getId());
        student2.setName("new One");
        mockMvc.perform(put("/students/")
                        .contentType("application/json")
                        .content(jackson.writeValueAsString(student2)))
                .andExpect(status().isOk());
        assertEquals(student.getId(), student2.getId());
        assertNotEquals(student.getName(), student2.getName());
        assertEquals(student.getAge(), student2.getAge());
        assertEquals(student.getCourse(), student2.getCourse());
        assertEquals(student.getGrade(), student2.getGrade());
    }

    @Test
    void deleteStudentSuccessTest() throws Exception {
        Course course = typicalCourse();
        courseService.save(course);

        Student student = typicalStudent(UUID.randomUUID());
        studentService.save(student);
        mockMvc.perform(delete("/students/")
                        .contentType("application/json")
                        .param("uuid", student.getId().toString()))
                .andExpect(status().isOk());
        assertThrows(NoSuchElementException.class, () -> studentService.find(student.getId()));
    }

    @Test
    void addStudentsToCourseSuccessTest() throws Exception {
        Course course = typicalCourse();
        courseService.save(course);

        Student student = typicalStudent(UUID.randomUUID());
        studentService.save(student);

        Course course2 = new Course(2, "TestCourse2", "description", 9);
        student.setCourse(course2);
        courseService.save(course2);

        UUID[] studentList = new UUID[1];
        studentList[0] = student.getId();
        StudentsToCourseMapper studentsToCourseMapper = new StudentsToCourseMapper(studentList, course2.getId());

        mockMvc.perform(patch("/students/add")
                        .contentType("application/json")
                        .content(jackson.writeValueAsString(studentsToCourseMapper)))
                .andExpect(status().isOk());

        assertEquals(studentService.find(student.getId()), student);
    }

    private Student typicalStudent(UUID uuid) {
        return new Student(uuid, "TestStudent", 19, typicalCourse(), 9);
    }

    private Course typicalCourse() {
        return new Course(1, "TestCourse1", "typical Course1", 4);
    }
}
