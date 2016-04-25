package netgloo.controllers.courseattendance;

import netgloo.domain.Classroom;
import netgloo.domain.Course;
import netgloo.domain.CourseAttendance;
import netgloo.domain.EnumStatus.Role;
import netgloo.domain.User;
import netgloo.domain.validator.CourseAttendanceCreateFormValidator;
import netgloo.service.classroom.ClassroomService;
import netgloo.service.course.CourseService;
import netgloo.service.courseattendance.CourseAttendanceService;
import netgloo.service.user.UserService;
import org.apache.tomcat.util.codec.binary.Base64;
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

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    private final UserService userService;
    private final CourseAttendanceCreateFormValidator courseAttendanceCreateFormValidator;

    private String fileUploadDirectory = "D:/image";
    private Course course;

    @Autowired
    public CourseAttendanceController(CourseAttendanceService courseAttendanceService, CourseService courseService, ClassroomService classroomService,
                                      UserService userService, CourseAttendanceCreateFormValidator courseAttendanceCreateFormValidator) {
        this.courseAttendanceService = courseAttendanceService;
        this.courseService = courseService;
        this.classroomService = classroomService;
        this.userService = userService;
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
    public String handleCourseAttendanceCreateForm(@Validated @ModelAttribute("form") CourseAttendance form, BindingResult bindingResult) {
        LOGGER.info("Processing CourseAttendance Create Form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            // failed validation
            return "courseattendance/courseattendance_create";
        }
        form.setCourse(course);
        courseAttendanceService.create(form);

        return "redirect:/course/" + course.getId() + "/attendance";
    }

    @PreAuthorize("hasAnyAuthority('GIAO_VIEN','ADMIN')")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.GET)
    public ModelAndView updateCourseAttendance(@PathVariable("id") long id) {
        CourseAttendance courseAttendance = courseAttendanceService.getCourseAttendanceById(id);
        if (courseAttendance == null) throw new NoSuchElementException(String.format("CourseAttendance=%s not found", id));
        return new ModelAndView("courseattendance/courseattendance_create", "form", courseAttendance);
    }

    @PreAuthorize("hasAnyAuthority('GIAO_VIEN','ADMIN')")
    @RequestMapping(value = "/{id}/update", method = RequestMethod.POST)
    public String handleUpdateCourseAttendance(@Validated @ModelAttribute("form") CourseAttendance form, BindingResult bindingResult) {
        LOGGER.info("Processing CourseAttendance form={}, bindingResult={}", form, bindingResult);
        if (bindingResult.hasErrors()) {
            return "courseattendance/courseattendance_create";
        }
        form.setCourse(course);
        courseAttendanceService.update(form);
        return "redirect:/course/" + course.getId() + "/attendance";
    }

    @PreAuthorize("hasAnyAuthority('GIAO_VIEN','ADMIN')")
    @RequestMapping(value = "/{id}/upload", method = RequestMethod.GET)
    public ModelAndView uploadImages(@PathVariable("id") long id) {
        CourseAttendance courseAttendance = courseAttendanceService.getCourseAttendanceById(id);
        if (courseAttendance == null) throw new NoSuchElementException(String.format("CourseAttendance=%s not found", id));
        return new ModelAndView("courseattendance/courseattendance_upload", "form", courseAttendance);
    }

    @PreAuthorize("hasAnyAuthority('GIAO_VIEN','ADMIN')")
    @RequestMapping(value = "/{id}/upload", method = RequestMethod.POST)
    public String handleUploadImages(@ModelAttribute("form") CourseAttendance form) {
        LOGGER.info("Processing handleUploadImages form={}", form);
        return "redirect:/course/" + course.getId() + "/attendance";
    }

    @PreAuthorize("hasAnyAuthority('GIAO_VIEN','ADMIN')")
    @RequestMapping(value = "/{id}/attendance", method = RequestMethod.GET)
    public ModelAndView attendance(@PathVariable("id") long id) {
        CourseAttendance courseAttendance = courseAttendanceService.getCourseAttendanceById(id);
        if (courseAttendance == null) throw new NoSuchElementException(String.format("CourseAttendance=%s not found", id));

        course = courseService.getCourseById(courseAttendance.getCourse().getId());
        List <User> sinhVienList = new ArrayList<>();
        for(User u : userService.getAllUsersInCourse(course.getId())) {
            if (u.getRole() == Role.USER) {
                sinhVienList.add(u);
            }
        }

        List<Integer> lst = Arrays.asList(1, 2);
        List<String> s = new ArrayList<>();
        try{
            for (int i = 0; i < lst.size(); i++) {
                Integer integer =  lst.get(i);
                File file = new File("D:/image/test/" + integer + ".jpg");
                FileInputStream fis=new FileInputStream(file);

                ByteArrayOutputStream bos=new ByteArrayOutputStream();
                int b;
                byte[] buffer = new byte[1024];

                while((b=fis.read(buffer))!=-1){
                    bos.write(buffer,0,b);
                }

                byte[] fileBytes=bos.toByteArray();
                fis.close();
                bos.close();
                byte[] encoded= Base64.encodeBase64(fileBytes);
                String encodedString = new String(encoded);

                s.add(encodedString);
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        ModelMap model = new ModelMap();
        model.addAttribute("imagesList", s);
        model.addAttribute("sinhVienList", sinhVienList);
        return new ModelAndView("courseattendance/courseattendance_attendance", "model", model);
    }
}
