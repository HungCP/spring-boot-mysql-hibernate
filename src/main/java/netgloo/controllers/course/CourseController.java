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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/{id}")
    public ModelAndView getClassroomPage(@PathVariable Long id) {
        ModelMap model = new ModelMap();

        Course course = courseService.getCourseById(id);
        User giaoVien = new User();
        List <User> sinhVienList = new ArrayList<>();
        List <CourseAttendance> courseAttendanceList = new ArrayList<>();

        LOGGER.info("userService size: " + userService.getAllUsersInCourse(id).size());

        for(User u : userService.getAllUsersInCourse(id)) {
            if(u.getRole() == Role.GIAO_VIEN) {
                giaoVien = u;
            } else if (u.getRole() == Role.USER) {
                sinhVienList.add(u);
            }
        }

        courseAttendanceList = courseAttendanceService.getAllCourseAttendanceInCourse(id);

        model.addAttribute("course", course);
        model.addAttribute("giaoVien", giaoVien);
        model.addAttribute("sinhVienList", sinhVienList);
        model.addAttribute("courseAttendanceList", courseAttendanceList);

        if (course == null) throw new NoSuchElementException(String.format("Course=%s not found", id));
        return new ModelAndView("course/course", "model", model);
    }
}
