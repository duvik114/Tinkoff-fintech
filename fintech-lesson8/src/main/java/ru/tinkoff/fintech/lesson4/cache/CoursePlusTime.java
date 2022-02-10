package ru.tinkoff.fintech.lesson4.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.tinkoff.fintech.lesson4.model.Course;

@Getter
@Setter
@AllArgsConstructor
public class CoursePlusTime {
    private Course course;
    private Long time;
}
