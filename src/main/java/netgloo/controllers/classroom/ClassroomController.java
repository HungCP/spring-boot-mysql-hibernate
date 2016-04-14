package netgloo.controllers.classroom;

import netgloo.domain.Classroom;
import netgloo.service.classroom.ClassroomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        return new ModelAndView("classroom/classroom", "classroom", classroom);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView getCourseAttendancePage() {
        return new ModelAndView("classroom/classroom_create", "form", new Classroom());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String handleUserCreateForm(@Validated @ModelAttribute("form") Classroom form, BindingResult bindingResult) {
        LOGGER.info("Processing classroom form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            return "classroom/classroom_create";
        }
        classroomService.create(form);
        return "redirect:/classrooms";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public ModelAndView updateUser(@PathVariable("id") long id) {
        Classroom classroom = classroomService.getClassroomById(id);
        if (classroom == null) throw new NoSuchElementException(String.format("Classroom=%s not found", id));
        return new ModelAndView("classroom/classroom_create", "form", classroom);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String handleUpdateUser(@Validated @ModelAttribute("form") Classroom form, BindingResult bindingResult) {
        LOGGER.info("Processing classroom form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            return "classroom/classroom_create";
        }
        classroomService.update(form);
        return "redirect:/classrooms";
    }
}
