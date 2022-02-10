package ru.tinkoff.fintech.lesson4.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.ibatis.annotations.*;
import ru.tinkoff.fintech.lesson4.model.Student;

@Mapper
public interface StudentRepository {

    List<Student> findAll();

    void save(Student student);

    void updateStudent(Student student);

    void deleteStudent(UUID id);

    @Select("SELECT id, name, age, course_id FROM students")
    @Results(value = {
        @Result(column = "id", property = "id"),
        @Result(column = "name", property = "name"),
        @Result(property = "age", column = "age"),
        @Result(property = "course_id", column = "course_id")
    })
    Optional<Student> findById(UUID id);

}
