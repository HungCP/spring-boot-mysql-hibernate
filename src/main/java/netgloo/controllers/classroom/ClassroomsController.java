package netgloo.controllers.classroom;

import netgloo.service.classroom.ClassroomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by G551 on 12/14/2015.
 */

@Controller
public class ClassroomsController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassroomsController.class);
    private final ClassroomService classroomService;

    @Autowired
    public ClassroomsController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @RequestMapping("/classrooms")
    public ModelAndView getClassroomsPage() {
        LOGGER.debug("Getting classrooms page");
        return new ModelAndView("classroom/classrooms", "classrooms", classroomService.getAllClassroom());
    }
}
