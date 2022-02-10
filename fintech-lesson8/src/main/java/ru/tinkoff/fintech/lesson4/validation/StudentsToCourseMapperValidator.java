package ru.tinkoff.fintech.lesson4.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ru.tinkoff.fintech.lesson4.model.StudentsToCourseMapper;
import ru.tinkoff.fintech.lesson4.service.StudentService;
import ru.tinkoff.fintech.lesson4.service.CourseService;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidator;

public class StudentsToCourseMapperValidator implements ConstraintValidator<StudentsToCourseMapperConstraint, StudentsToCourseMapper> {

    @Autowired
    private CourseService courseService;
    @Autowired
    private StudentService studentService;

    @Override
    public boolean isValid(StudentsToCourseMapper studentsToCourseMapper, ConstraintValidatorContext constraintValidatorContext) {
        for (var sid : studentsToCourseMapper.getStudentIdList()) {
            if (studentService.find(sid).getGrade() < courseService.find(studentsToCourseMapper
                    .getCourseId()).getRequiredGrade()) {
                return false;
            }
        }
        return true;
    }

}
