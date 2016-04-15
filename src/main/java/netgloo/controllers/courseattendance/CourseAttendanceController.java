package netgloo.controllers.courseattendance;

import netgloo.domain.Course;
import netgloo.domain.CourseAttendance;
import netgloo.domain.Image;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Iterator;
import java.util.LinkedList;
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
        course = courseService.getCourseById(course_id);
        return new ModelAndView("courseattendance/courseattendance_create", "form", new CourseAttendance(course));
    }

    @PreAuthorize("hasAnyAuthority('GIAO_VIEN','ADMIN')")
    @RequestMapping(value = "/create/{course_id}", method = RequestMethod.POST)
    public String handleCourseAttendanceCreateForm(@Valid @ModelAttribute("form") CourseAttendance form, BindingResult bindingResult,
                                                   MultipartHttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Processing CourseAttendance Create Form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "courseattendance/courseattendance_create";
        }
        form.setCourse(course);
        //courseAttendanceService.create(form);

        // xử lý lưu image
        System.out.println("xử lý lưu image= : "+form.getFiles());

        Iterator<String> itr = request.getFileNames();

        System.out.println("itr= : "+itr.getClass());

        MultipartFile mpf;
        List<Image> list = new LinkedList<>();
        while (itr.hasNext()) {
            mpf = request.getFile(itr.next());
            System.out.println("mpf= : "+mpf);
            //System.out.println(mpf.getOriginalFilename() +" uploaded! "+files.size());
        }

        return "redirect:/course/" + form.getCourse().getId();
    }

}
