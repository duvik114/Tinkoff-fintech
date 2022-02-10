package ru.tinkoff.fintech.lesson4.service;

import org.springframework.stereotype.Service;
import ru.tinkoff.fintech.lesson4.dao.CourseRepository;
import ru.tinkoff.fintech.lesson4.model.Course;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public Course find(int id) {
        return repository.findById(id).orElseThrow();
    }

    public int findOldestByStudentsAgesCourse() {
        return repository.getOldestCourse();
    }

    public void save(Course Course) {
        repository.save(Course);
    }

    public void update(Course Course) {
        repository.updateCourse(Course);
    }

    public void delete(int id) {
        repository.deleteCourse(id);
    }

    public List<Course> findAll() {
        return repository.findAll();
    }
}
