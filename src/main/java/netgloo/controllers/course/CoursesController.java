package netgloo.controllers.course;

import netgloo.domain.CurrentUser;
import netgloo.domain.User;
import netgloo.service.course.CourseService;
import netgloo.service.currentuser.CurrentUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by G551 on 12/15/2015.
 */
@Controller
public class CoursesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoursesController.class);
    private final CourseService courseService;

    @Autowired
    public CoursesController(CourseService courseService, CurrentUserService currentUserService) {
        this.courseService = courseService;
    }

    @RequestMapping("/courses")
    public ModelAndView getCoursesPage() {
        LOGGER.debug("Getting courses page");
        return new ModelAndView("course/courses", "courses", courseService.getAllCourses());
    }

    @RequestMapping("/courses/my-courses")
    public ModelAndView getMyCoursesPage() {
        CurrentUser u = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ModelAndView("course/mycourses", "courses", courseService.getMyCourses(u.getId()));
    }
}
