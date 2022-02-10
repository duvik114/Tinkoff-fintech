package ru.tinkoff.fintech.lesson4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentsToCourseMapper {

    @Size(min = 1)
    private UUID[] studentIdList;

    @Min(1)
    private int courseId;
}
