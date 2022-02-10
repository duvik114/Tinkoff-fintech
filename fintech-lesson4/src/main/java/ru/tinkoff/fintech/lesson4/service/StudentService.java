package ru.tinkoff.fintech.lesson4.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import ru.tinkoff.fintech.lesson4.dao.StudentRepository;
import ru.tinkoff.fintech.lesson4.model.Student;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student findStudent(UUID id) {
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
}
