package ru.tinkoff.fintech.lesson4.service;

import java.util.*;

import org.springframework.stereotype.Service;
import ru.tinkoff.fintech.lesson4.dao.StudentRepository;
import ru.tinkoff.fintech.lesson4.model.Student;
import ru.tinkoff.fintech.lesson4.model.StudentsToCourseMapper;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student find(UUID id) {
        return repository.findById(id).orElseThrow();
    }

    public void save(Student student) {
        repository.save(student);
    }

    public void update(Student student) {
        repository.updateStudent(student);
    }

    public void delete(UUID id) {
        repository.deleteStudent(id);
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

    public void addStudentsToCourse(StudentsToCourseMapper studentsToCourseMapper) {
        repository.addStudentsToCourse(studentsToCourseMapper);
    }
}
