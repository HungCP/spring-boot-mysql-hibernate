package netgloo.controllers.courseattendance;

import netgloo.domain.Classroom;
import netgloo.domain.Course;
import netgloo.domain.CourseAttendance;
import netgloo.domain.validator.CourseAttendanceCreateFormValidator;
import netgloo.service.classroom.ClassroomService;
import netgloo.service.course.CourseService;
import netgloo.service.courseattendance.CourseAttendanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by G551 on 03/22/2016.
 */

@Controller
@RequestMapping("/courseAttendance")
public class CourseAttendanceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseAttendanceController.class);
    private final CourseAttendanceService courseAttendanceService;
    private final CourseService courseService;
    private final ClassroomService classroomService;
    private final CourseAttendanceCreateFormValidator courseAttendanceCreateFormValidator;

    private String fileUploadDirectory = "D:/image";
    private Course course;

    @Autowired
    public CourseAttendanceController(CourseAttendanceService courseAttendanceService, CourseService courseService, ClassroomService classroomService,
                                      CourseAttendanceCreateFormValidator courseAttendanceCreateFormValidator) {
        this.courseAttendanceService = courseAttendanceService;
        this.courseService = courseService;
        this.classroomService = classroomService;
        this.courseAttendanceCreateFormValidator = courseAttendanceCreateFormValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(courseAttendanceCreateFormValidator);
    }

    @RequestMapping("/{id}")
    public ModelAndView getClassroomPage(@PathVariable Long id) {
        ModelMap model = new ModelMap();
        CourseAttendance courseAttendance = courseAttendanceService.getCourseAttendanceById(id);
        course = courseService.getCourseById(courseAttendance.getCourse().getId());

        model.addAttribute("courseAttendance", courseAttendance);
        model.addAttribute("course", course);
        if (courseAttendance == null)
            throw new NoSuchElementException(String.format("Course Attendance=%s not found", id));
        return new ModelAndView("courseattendance/courseattendance", "model", model);
    }

    @PreAuthorize("hasAnyAuthority('GIAO_VIEN','ADMIN')")
    @RequestMapping(value = "/create/{course_id}", method = RequestMethod.GET)
    public ModelAndView getCourseAttendancePage(@PathVariable("course_id") Long course_id) {
        ModelMap model = new ModelMap();
        course = courseService.getCourseById(course_id);
        List<Classroom> classroomsList = classroomService.getAllClassroom();
        model.addAttribute("classroomsList", classroomsList);
        model.addAttribute("form", loadCourseAttendance());
        return new ModelAndView("courseattendance/courseattendance_create", "model", model);
    }

    @ModelAttribute("form")
    public CourseAttendance loadCourseAttendance(){
        return new CourseAttendance(course);
    }

    @PreAuthorize("hasAnyAuthority('GIAO_VIEN','ADMIN')")
    @RequestMapping(value = "/create/{course_id}", method = RequestMethod.POST)
    public String handleCourseAttendanceCreateForm(@Validated @ModelAttribute("form") CourseAttendance form, BindingResult bindingResult) {
        LOGGER.info("Processing CourseAttendance Create Form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "courseattendance/courseattendance_create";
        }
        form.setCourse(course);
        courseAttendanceService.create(form);

        return "redirect:/course/" + form.getCourse().getId() + "/attendance";
    }

    @PreAuthorize("hasAnyAuthority('GIAO_VIEN','ADMIN')")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public ModelAndView updateCourseAttendance(@PathVariable("id") long id) {
        CourseAttendance courseAttendance = courseAttendanceService.getCourseAttendanceById(id);
        if (courseAttendance == null) throw new NoSuchElementException(String.format("Classroom=%s not found", id));
        return new ModelAndView("courseattendance/courseattendance_create", "form", courseAttendance);
    }

    /*@PreAuthorize("hasAnyAuthority('GIAO_VIEN','ADMIN')")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String handleUpdateCourseAttendance(@Validated @ModelAttribute("form") CourseAttendance form, BindingResult bindingResult) {
        LOGGER.info("Processing classroom form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            return "classroom/classroom_create";
        }
        classroomService.update(form);
        return "redirect:/course/" + form.getCourse().getId() + "/attendance";
    }*/

    @PreAuthorize("hasAnyAuthority('GIAO_VIEN','ADMIN')")
    @RequestMapping(value = "/{id}/upload", method = RequestMethod.GET)
    public ModelAndView uploadImages(@PathVariable("id") long id) {
        CourseAttendance courseAttendance = courseAttendanceService.getCourseAttendanceById(id);
        if (courseAttendance == null) throw new NoSuchElementException(String.format("Classroom=%s not found", id));
        return new ModelAndView("courseattendance/courseattendance_upload", "form", courseAttendance);
    }

    @PreAuthorize("hasAnyAuthority('GIAO_VIEN','ADMIN')")
    @RequestMapping(value = "/{id}/upload", method = RequestMethod.POST)
    public String handleUploadImages(@ModelAttribute("form") CourseAttendance form) {
        LOGGER.info("Processing handleUploadImages form={}", form);
        return "redirect:/course/" + form.getCourse().getId() + "/attendance";
    }
}
