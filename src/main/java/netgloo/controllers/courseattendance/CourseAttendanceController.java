package netgloo.controllers.courseattendance;

import netgloo.domain.form.CourseAttendanceCreateForm;
import netgloo.service.courseattendance.CourseAttendanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by G551 on 03/22/2016.
 */

@Controller
public class CourseAttendanceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseAttendanceController.class);
    private final CourseAttendanceService courseAttendanceService;

    @Autowired
    public CourseAttendanceController(CourseAttendanceService courseAttendanceService) {
        this.courseAttendanceService = courseAttendanceService;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/courseAttendance/{course_id}/create", method = RequestMethod.GET)
    public ModelAndView getCourseAttendancePage() {
        return new ModelAndView("courseattendance/courseattendance_create", "form", new CourseAttendanceCreateForm());
    }

}
