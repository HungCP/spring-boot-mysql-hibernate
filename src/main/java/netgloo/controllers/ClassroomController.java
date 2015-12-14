package netgloo.controllers;

import netgloo.domain.Classroom;
import netgloo.service.classroom.ClassroomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

/**
 * Created by G551 on 12/14/2015.
 */
@Controller
@RequestMapping("/classroom")
public class ClassroomController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassroomController.class);
    private final ClassroomService classroomService;

    @Autowired
    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @RequestMapping("/{id}")
    public ModelAndView getClassroomPage(@PathVariable Long id) {
        LOGGER.debug("Getting classroom page for classroom={}", id);
        LOGGER.info("Getting classroom page for classroom={}" + id);
        Classroom classroom = classroomService.getClassroomById(id);
        if (classroom == null) throw new NoSuchElementException(String.format("Classroom=%s not found", id));
        return new ModelAndView("classroom", "classroom", classroom);
    }
}
