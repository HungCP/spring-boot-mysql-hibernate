package netgloo.controllers.course;

import netgloo.domain.Course;
import netgloo.domain.CourseAttendance;
import netgloo.domain.EnumStatus.Role;
import netgloo.domain.User;
import netgloo.service.course.CourseService;
import netgloo.service.courseattendance.CourseAttendanceService;
import netgloo.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by G551 on 12/15/2015.
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);
    private final CourseService courseService;
    private final UserService userService;
    private final CourseAttendanceService courseAttendanceService;

    @Autowired
    public CourseController(CourseService courseService, UserService userService, CourseAttendanceService courseAttendanceService) {
        this.courseService = courseService;
        this.userService = userService;
        this.courseAttendanceService = courseAttendanceService;
    }

    @RequestMapping("/{id}/attendance")
    public ModelAndView getClassroomPage(@PathVariable Long id) {
        ModelMap model = new ModelMap();

        Course course = courseService.getCourseById(id);
        User giaoVien = new User();
        List <User> sinhVienList = new ArrayList<>();
        List <CourseAttendance> courseAttendanceList = courseAttendanceService.getAllCourseAttendanceInCourse(id);

        for(User u : userService.getAllUsersInCourse(id)) {
            if(u.getRole() == Role.GIAO_VIEN) {
                giaoVien = u;
            } else if (u.getRole() == Role.USER) {
                sinhVienList.add(u);
            }
        }

        model.addAttribute("course", course);
        model.addAttribute("giaoVien", giaoVien);
        model.addAttribute("sinhVienList", sinhVienList);
        model.addAttribute("courseAttendanceList", courseAttendanceList);

        if (course == null) throw new NoSuchElementException(String.format("Course=%s not found", id));
        return new ModelAndView("course/attendance", "model", model);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView getCourseCreatePage() {
        LOGGER.debug("Getting course create form");
        return new ModelAndView("course/course_create", "model", new Course());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String handleCourseCreateForm(@Validated @ModelAttribute("model") Course model, BindingResult bindingResult) {
        LOGGER.info("Processing course create form={}, bindingResult={}", model, bindingResult);
        if (bindingResult.hasErrors()) {
            return "course/course_create";
        }
        courseService.create(model);
        return "redirect:/courses";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public ModelAndView updateCourse(@PathVariable("id") long id) {
        Course course = courseService.getCourseById(id);
        if (course == null) throw new NoSuchElementException(String.format("Classroom=%s not found", id));
        return new ModelAndView("course/course_create", "model", course);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String handleUpdateCourse(@Validated @ModelAttribute("model") Course model, BindingResult bindingResult) {
        LOGGER.info("Processing classroom form={}, bindingResult={}", model, bindingResult);
        if (bindingResult.hasErrors()) {
            return "course/course_create";
        }
        if(!courseService.isFieldUnique(model.getMa(), model.getId())) {
            bindingResult.rejectValue("ma", "exists.model.ma");
            return "course/course_create";
        }
        courseService.update(model);
        return "redirect:/courses";
    }
}
