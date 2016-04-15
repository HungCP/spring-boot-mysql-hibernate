package netgloo.domain.validator;

import netgloo.domain.Course;
import netgloo.service.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by G551 on 04/15/2016.
 */
public class CourseCreateFormValidator implements Validator {

    @Autowired
    CourseService courseService;

    @Override
    public boolean supports(Class<?> clazz) {
        return CourseService.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Course course = (Course) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.model.name");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ma", "NotEmpty.model.ma");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "courseStatus", "NotEmpty.model.courseStatus");

    }
}
