package ru.tinkoff.fintech.lesson4.controller.courseTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tinkoff.fintech.lesson4.Lesson4Application;
import ru.tinkoff.fintech.lesson4.model.Course;
import org.springframework.test.web.servlet.MockMvc;
import ru.tinkoff.fintech.lesson4.model.Student;
import ru.tinkoff.fintech.lesson4.service.CourseService;
import ru.tinkoff.fintech.lesson4.service.StudentService;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Lesson4Application.class)
@AutoConfigureMockMvc
class CourseDBOperationsTest {

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
    void setCourseSuccessTest() throws Exception {
        Course course = typicalCourse();
        var courseJson = jackson.writeValueAsString(course);
        mockMvc.perform(post("/courses/")
                        .contentType("application/json")
                        .content(courseJson))
                .andExpect(status().isOk());
        Course course2 = courseService.find(1);
        assertEquals(course, course2);
    }

    @Test
    void getCourseSuccessTest() throws Exception {
        Course course = typicalCourse();
        courseService.save(course);

        mockMvc.perform(get("/courses/")
                        .contentType("application/json")
                        .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string(jackson.writeValueAsString(course)));
    }

    @Test
    void updateCourseSuccessTest() throws Exception {
        Course course = typicalCourse();
        courseService.save(course);
        Course course2 = requiresCourse(1, 9);
        mockMvc.perform(put("/courses/")
                        .contentType("application/json")
                        .content(jackson.writeValueAsString(course2)))
                .andExpect(status().isOk());
        course2 = courseService.find(1);

        assertEquals(course.getId(), course2.getId());
        assertEquals(course.getName(), course2.getName());
        assertEquals(course.getDescription(), course2.getDescription());
        assertNotEquals(course.getRequiredGrade(), course2.getRequiredGrade());
    }

    @Test
    void deleteCourseSuccessTest() throws Exception {
        Course course = typicalCourse();
        courseService.save(course);
        mockMvc.perform(delete("/courses/")
                        .contentType("application/json")
                        .param("id", "1"))
                .andExpect(status().isOk());
        assertThrows(NoSuchElementException.class, () -> courseService.find(course.getId()));
    }

    private Course typicalCourse() {
        return new Course(1, "TestCourse1", "typical Course1", 4);
    }

    private Course requiresCourse(int id, int requiredGrade) {
        return new Course(id, "TestCourse" + id, "typical Course" + id, requiredGrade);
    }
}
