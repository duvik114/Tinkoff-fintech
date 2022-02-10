package ru.tinkoff.fintech.lesson4;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.tinkoff.fintech.lesson4.model.Course;
import ru.tinkoff.fintech.lesson4.service.CourseService;
import ru.tinkoff.fintech.lesson4.service.StudentService;
import ru.tinkoff.fintech.lesson4.model.Student;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class Lesson4Application {

    public static void main(String[] args) {
        SpringApplication.run(Lesson4Application.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(StudentService studentService, CourseService courseService) {
        return args -> {
            //create our course
            Course course1 = new Course(1, "Java", "Fintech Java", 4);
            Course course2 = new Course(2, "C/C++", "Skakov)", 9);

            //add to table
            courseService.save(course1);
            courseService.save(course2);

            //find courses
            course1 = courseService.find(2);
            course2 = courseService.find(1);

            // create and save John
            Student john = new Student(UUID.randomUUID(), "John", 27, course1, 5);
            studentService.save(john);

            // find John by id and assert fields
            Student actual = studentService.find(john.getId());
            assert john.equals(actual);

            studentService.delete(john.getId());

            // create and save Mary
            Student mary = new Student(UUID.randomUUID(), "Mary", 19, course1, 6);
            studentService.save(mary);

            studentService.update(new Student(mary.getId(), "sus", 19, course2, 7));
            studentService.save(john);

            //oldest Course test
            Student biba = new Student(UUID.randomUUID(), "biba", 19, course1, 8);
            Student boba = new Student(UUID.randomUUID(), "boba", 20, course2, 10);
            studentService.save(biba);
            studentService.save(boba);
            System.out.println(courseService.find(courseService.findOldestByStudentsAgesCourse()));

            // find all students
            List<Student> allStudents = studentService.findAll();
            System.out.println(allStudents);

            // find all courses
            List<Course> allCourses = courseService.findAll();
            System.out.println(allCourses);

            /*//Index test
            Course course3 = new Course(3, "puk", "chik", 345);
            Course course4 = new Course(4, "lol", "kek", 2342);
            courseService.save(course3);
            courseService.save(course4);
            for (int i = 0; i < 100000; i++) {
                Course course;
                if (i % 4 == 0) {
                    course = course1;
                } else if (i % 4 == 1) {
                    course = course2;
                } else if (i % 4 == 2) {
                    course = course3;
                } else {
                    course = course4;
                }
                studentService.save(
                        new Student(UUID.randomUUID(), "name " + i, (100000 - i) % 10000, course, 1));
            }

            System.out.println("finished");
            System.out.println(courseService.findOldestByStudentsAgesCourse());*/
        };
    }
}
