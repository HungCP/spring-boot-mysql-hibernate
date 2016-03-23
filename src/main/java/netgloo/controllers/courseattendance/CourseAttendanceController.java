package netgloo.controllers.courseattendance;

import netgloo.domain.Course;
import netgloo.domain.form.CourseAttendanceCreateForm;
import netgloo.service.course.CourseService;
import netgloo.service.courseattendance.CourseAttendanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final CourseService courseService;

    @Autowired
    public CourseAttendanceController(CourseAttendanceService courseAttendanceService, CourseService courseService) {
        this.courseAttendanceService = courseAttendanceService;
        this.courseService = courseService;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/courseAttendance/create/{course_id}", method = RequestMethod.GET)
    public ModelAndView getCourseAttendancePage(@PathVariable("course_id") Long course_id) {
        Course course = courseService.getCourseById(course_id);
        return new ModelAndView("courseattendance/courseattendance_create", "form", new CourseAttendanceCreateForm(course));
    }

}
