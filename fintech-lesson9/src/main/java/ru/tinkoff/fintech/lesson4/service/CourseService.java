package ru.tinkoff.fintech.lesson4.service;

import org.springframework.stereotype.Service;
import ru.tinkoff.fintech.lesson4.cache.CourseCache;
import ru.tinkoff.fintech.lesson4.cache.CoursePlusTime;
import ru.tinkoff.fintech.lesson4.dao.CourseRepository;
import ru.tinkoff.fintech.lesson4.model.Course;
import java.util.Date;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository repository;
    private final CourseCache courseCache;

    public CourseService(CourseRepository repository, CourseCache courseCache) {
        this.repository = repository;
        this.courseCache = courseCache;
    }

    public Course find(int id) {
        Course course = courseCache.find(id);
        if (course == null) {
            course = repository.findById(id).orElseThrow();
            courseCache.putIfAbsent(new CoursePlusTime(course, new Date().getTime()));
            course = courseCache.find(id);
        }
        return course;
    }

    public int findOldestByStudentsAgesCourse() {
        return repository.getOldestCourse();
    }

    public void save(Course course) {
        repository.save(course);
        courseCache.putIfAbsent(new CoursePlusTime(course, new Date().getTime()));
    }

    public void update(Course course) {
        repository.updateCourse(course);
        courseCache.put(new CoursePlusTime(course, new Date().getTime()));
    }

    public void delete(int id) {
        repository.deleteCourse(id);
        courseCache.delete(id, new Date().getTime());
    }

    public List<Course> findAll() {
        return repository.findAll();
    }

    public void clearCache() {
        courseCache.clearCache();
    }
}