package netgloo.controllers.courseattendance;

import netgloo.domain.Course;
import netgloo.domain.CourseAttendance;
import netgloo.domain.form.CourseAttendanceCreateForm;
import netgloo.domain.validator.CourseAttendanceCreateFormValidator;
import netgloo.service.course.CourseService;
import netgloo.service.courseattendance.CourseAttendanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.NoSuchElementException;

/**
 * Created by G551 on 03/22/2016.
 */

@Controller
public class CourseAttendanceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseAttendanceController.class);
    private final CourseAttendanceService courseAttendanceService;
    private final CourseService courseService;
    private final CourseAttendanceCreateFormValidator courseAttendanceCreateFormValidator;

    private Course course;

    @Autowired
    public CourseAttendanceController(CourseAttendanceService courseAttendanceService, CourseService courseService, CourseAttendanceCreateFormValidator courseAttendanceCreateFormValidator) {
        this.courseAttendanceService = courseAttendanceService;
        this.courseService = courseService;
        this.courseAttendanceCreateFormValidator = courseAttendanceCreateFormValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(courseAttendanceCreateFormValidator);
    }

    @RequestMapping("/courseAttendance/{id}")
    public ModelAndView getClassroomPage(@PathVariable Long id) {
        ModelMap model = new ModelMap();
        CourseAttendance courseAttendance = courseAttendanceService.getCourseAttendanceById(id);
        Course course = courseService.getCourseById(courseAttendance.getCourse().getId());
        model.addAttribute("courseAttendance", courseAttendance);
        model.addAttribute("course", course);
        if (courseAttendance == null) throw new NoSuchElementException(String.format("Course Attendance=%s not found", id));
        return new ModelAndView("courseattendence/courseattendence", "model", model);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/courseAttendance/create/{course_id}", method = RequestMethod.GET)
    public ModelAndView getCourseAttendancePage(@PathVariable("course_id") Long course_id) {
        course = courseService.getCourseById(course_id);
        return new ModelAndView("courseattendance/courseattendance_create", "form", new CourseAttendanceCreateForm(course));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/courseAttendance/create/{course_id}", method = RequestMethod.POST)
    public String handleCourseAttendanceCreateForm(@Valid @ModelAttribute("form") CourseAttendanceCreateForm form, BindingResult bindingResult) {
        LOGGER.info("Processing CourseAttendance Create Form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "courseattendance/courseattendance_create";
        }
        form.setCourse(course);
        courseAttendanceService.create(form);
        return "redirect:/course/" + form.getCourse().getId();
    }

}
