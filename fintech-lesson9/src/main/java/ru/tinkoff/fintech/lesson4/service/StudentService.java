package ru.tinkoff.fintech.lesson4.service;

import java.nio.ByteBuffer;
import java.util.*;

import org.springframework.stereotype.Service;
import ru.tinkoff.fintech.lesson4.dao.StudentRepository;
import ru.tinkoff.fintech.lesson4.model.Course;
import ru.tinkoff.fintech.lesson4.model.Student;
import ru.tinkoff.fintech.lesson4.model.StudentsToCourseMapper;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student find(UUID id) {
        HashMap<String, Object> resultMap = repository.findById(id).orElseThrow();
        ByteBuffer bb = ByteBuffer.wrap((byte[]) resultMap.get("ID"));
        long high = bb.getLong();
        long low = bb.getLong();
        UUID uuid = new UUID(high, low);
        return new Student(uuid,
                (String) resultMap.get("NAME"),
                (int) resultMap.get("AGE"),
                new Course((int) resultMap.get("COURSEID"),
                        (String) resultMap.get("COURSENAME"),
                        (String) resultMap.get("COURSEDESCRIPTION"),
                        (int) resultMap.get("COURSEREQUIREDGRADE")),
                (int) resultMap.get("GRADE"));
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
