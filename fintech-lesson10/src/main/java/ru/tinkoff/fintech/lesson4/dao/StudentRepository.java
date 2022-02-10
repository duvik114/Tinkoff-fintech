package ru.tinkoff.fintech.lesson4.dao;

import java.util.*;

import org.apache.ibatis.annotations.*;
import ru.tinkoff.fintech.lesson4.model.Student;
import ru.tinkoff.fintech.lesson4.model.StudentsToCourseMapper;

@Mapper
public interface StudentRepository {

    List<Student> findAll();

    void save(Student student);

    void updateStudent(Student student);

    void deleteStudent(UUID id);

    Optional<HashMap<String, Object>> findById(UUID id);

    void addStudentsToCourse(StudentsToCourseMapper studentsToCourseMapper);

}
