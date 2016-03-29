package netgloo.domain.validator;

import netgloo.domain.form.CourseAttendanceCreateForm;
import netgloo.service.courseattendance.CourseAttendanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by G551 on 03/25/2016.
 */

@Component
public class CourseAttendanceCreateFormValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseAttendanceCreateFormValidator.class);
    private final CourseAttendanceService courseAttendanceService;

    @Autowired
    public CourseAttendanceCreateFormValidator(CourseAttendanceService courseAttendanceService) {
        this.courseAttendanceService = courseAttendanceService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(CourseAttendanceCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        CourseAttendanceCreateForm form = (CourseAttendanceCreateForm) target;
    }

}
