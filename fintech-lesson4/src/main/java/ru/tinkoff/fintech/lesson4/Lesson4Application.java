package ru.tinkoff.fintech.lesson4;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.tinkoff.fintech.lesson4.model.Course;
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
    ApplicationRunner applicationRunner(StudentService service) {
        return args -> {
            //create our course
            Course course1 = new Course("Java", "Fintech Java");
            Course course2 = new Course("C/C++", "Skakov)");

            // create and save John
            Student john = new Student(UUID.randomUUID(), "John", 27, course1);
            service.save(john);

            // find John by id and assert fields
            Student actual = service.findStudent(john.getId());
            assert john.equals(actual);

            service.delete(john.getId());

            // create and save Mary
            Student mary = new Student(UUID.randomUUID(), "Mary", 19, course1);
            service.save(mary);

            service.update(new Student(mary.getId(), "sus", 19, course2));

            // find all students
            List<Student> all = service.findAll();
            System.out.println(all);
        };
    }

}
