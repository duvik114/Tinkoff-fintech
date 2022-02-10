package ru.tinkoff.fintech.lesson4.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StudentsToCourseMapperValidator.class)
public @interface StudentsToCourseMapperConstraint {

    String message() default "Student entity is not valid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

