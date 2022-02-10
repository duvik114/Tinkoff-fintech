package ru.tinkoff.fintech.lesson4.controller.courseCacheTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.tinkoff.fintech.lesson4.Lesson4Application;
import ru.tinkoff.fintech.lesson4.cache.CourseCache;
import ru.tinkoff.fintech.lesson4.dao.CourseRepository;
import ru.tinkoff.fintech.lesson4.model.Course;
import ru.tinkoff.fintech.lesson4.model.Student;
import ru.tinkoff.fintech.lesson4.service.CourseService;
import ru.tinkoff.fintech.lesson4.service.StudentService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = Lesson4Application.class)
public class CourseCacheTest {

    @Autowired
    private StudentService studentService;
    private final CourseRepository courseRepository = mock(CourseRepository.class);
    private final CourseCache courseCache = new CourseCache();
    private final CourseService courseService = new CourseService(courseRepository, courseCache);

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
    void courseNotAddedToCacheTest() {
        Course course = typicalCourse();
        assertNull(courseCache.find(course.getId()));
        when(courseRepository.findById(course.getId())).thenReturn(java.util.Optional.of(course));
        course = courseService.find(course.getId());
        verify(courseRepository).findById(course.getId());
        assertEquals(courseCache.find(course.getId()), course);
    }

    @Test // courseCreateTest is here too
    void courseAlreadyAddedToCacheTest() {
        Course course = typicalCourse();
        assertNull(courseCache.find(course.getId()));
        courseService.save(course);
        assertEquals(courseCache.find(course.getId()), course);
        course = courseService.find(course.getId());
        verify(courseRepository, times(0)).findById(course.getId());
    }

    @Test
    void courseUpdateCacheTest() {
        Course course = typicalCourse();
        assertNull(courseCache.find(course.getId()));
        courseService.save(course);
        assertEquals(courseCache.find(course.getId()), course);
        course.setName("new Name");
        courseService.update(course);
        assertEquals(courseCache.find(course.getId()), course);
    }

    @Test
    void courseDeleteCacheTest() {
        Course course = typicalCourse();
        assertNull(courseCache.find(course.getId()));
        courseService.save(course);
        assertEquals(courseCache.find(course.getId()), course);
        courseService.delete(course.getId());
        assertNull(courseCache.find(course.getId()));
    }

    private Course typicalCourse() {
        return new Course(1, "TestCourse1", "typical Course1", 4);
    }
}
