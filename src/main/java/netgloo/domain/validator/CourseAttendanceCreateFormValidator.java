package netgloo.domain.validator;

import netgloo.domain.CourseAttendance;
import netgloo.service.courseattendance.CourseAttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by G551 on 03/25/2016.
 */

@Component
public class CourseAttendanceCreateFormValidator implements Validator {

    @Autowired
    @Qualifier("emailValidator")
    EmailValidator emailValidator;

    @Autowired
    CourseAttendanceService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return CourseAttendance.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        CourseAttendance courseAttendance = (CourseAttendance) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.form.name");

    }

}
