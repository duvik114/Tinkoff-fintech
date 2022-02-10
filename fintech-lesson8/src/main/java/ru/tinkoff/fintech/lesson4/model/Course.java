package ru.tinkoff.fintech.lesson4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Min(1)
    private int id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @Min(0)
    private int requiredGrade;
}
