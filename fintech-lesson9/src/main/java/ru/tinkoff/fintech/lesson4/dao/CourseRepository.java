package ru.tinkoff.fintech.lesson4.dao;

import org.apache.ibatis.annotations.Mapper;
import ru.tinkoff.fintech.lesson4.model.Course;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CourseRepository {

    List<Course> findAll();

    void save(Course course);

    void updateCourse(Course course);

    void deleteCourse(int id);

    Optional<Course> findById(int id);

    int getOldestCourse();

}
