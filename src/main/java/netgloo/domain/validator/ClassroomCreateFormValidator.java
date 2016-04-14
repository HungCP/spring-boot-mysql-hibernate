package netgloo.domain.validator;

import netgloo.domain.Classroom;
import netgloo.domain.CourseAttendance;
import netgloo.service.classroom.ClassroomService;
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
public class ClassroomCreateFormValidator implements Validator {

    @Autowired
    ClassroomService classroomService;

    @Override
    public boolean supports(Class<?> clazz) {
        return ClassroomService.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Classroom classroom = (Classroom) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.form.name");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ma", "NotEmpty.form.ma");

    }

}
