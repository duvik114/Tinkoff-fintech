package ru.tinkoff.fintech.lesson4.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @NotNull
    private UUID id;

    @NotEmpty
    private String name;

    @Min(1)
    private int age;

    @NotNull
    private Course course;

    @Min(0)
    private int grade;

    public int getCourse_id() {
        return course.getId();
    }

}
