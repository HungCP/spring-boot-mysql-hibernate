package netgloo.controllers.course;

import netgloo.domain.Course;
import netgloo.service.course.CourseService;
import netgloo.service.user_course.UserCourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

/**
 * Created by G551 on 12/15/2015.
 */
@Controller
@RequestMapping("/course")
public class CourseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseController.class);
    private final CourseService courseService;
    private final UserCourseService userCourseService;

    @Autowired
    public CourseController(CourseService courseService, UserCourseService userCourseService) {
        this.courseService = courseService;
        this.userCourseService = userCourseService;
    }

    @RequestMapping("/{id}")
    public ModelAndView getClassroomPage(@PathVariable Long id) {
        LOGGER.debug("Getting classroom page for classroom={}", id);
        LOGGER.info("Getting classroom page for classroom={}" + id);

        ModelMap model = new ModelMap();

        Course course = courseService.getCourseById(id);
        model.addAttribute("course", course);

        LOGGER.info("List User: " + userCourseService.getAllUsersByCourse(id));

        if (course == null) throw new NoSuchElementException(String.format("Course=%s not found", id));
        return new ModelAndView("course/course", "model", model);
    }
}
