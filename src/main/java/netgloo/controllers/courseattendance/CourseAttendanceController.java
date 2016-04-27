package netgloo.controllers.courseattendance;

import netgloo.domain.*;
import netgloo.domain.EnumStatus.ModelStatus;
import netgloo.domain.EnumStatus.Role;
import netgloo.domain.validator.CourseAttendanceCreateFormValidator;
import netgloo.service.classroom.ClassroomService;
import netgloo.service.course.CourseService;
import netgloo.service.courseattendance.CourseAttendanceService;
import netgloo.service.image.ImageService;
import netgloo.service.user.UserService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.imgscalr.Scalr;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
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
    private final ImageService imageService;
    private final CourseAttendanceCreateFormValidator courseAttendanceCreateFormValidator;

    private String fileUploadDirectory = "D:/image/test/";
    private Course course;

    @Autowired
    public CourseAttendanceController(CourseAttendanceService courseAttendanceService, CourseService courseService, ClassroomService classroomService,
                                      UserService userService, ImageService imageService, CourseAttendanceCreateFormValidator courseAttendanceCreateFormValidator) {
        this.courseAttendanceService = courseAttendanceService;
        this.courseService = courseService;
        this.classroomService = classroomService;
        this.userService = userService;
        this.imageService = imageService;
        this.courseAttendanceCreateFormValidator = courseAttendanceCreateFormValidator;
    }

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(courseAttendanceCreateFormValidator);
    }

    @ModelAttribute("classroomsList")
    public List<Classroom> classroomsList(){
        return classroomService.getAllClassroom();
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
        if (courseAttendance == null)
            throw new NoSuchElementException(String.format("CourseAttendance=%s not found", id));
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

    /*@ModelAttribute("file")
    public List<Image> getImagesList(){
        return imageService.getImagesByCourseAttendance(courseAttendance.getId());
    }*/

    @PreAuthorize("hasAnyAuthority('GIAO_VIEN','ADMIN')")
    @RequestMapping(value = "/{id}/upload", method = RequestMethod.GET)
    public @ResponseBody ModelAndView uploadImages(@PathVariable("id") long id) {
        CourseAttendance courseAttendance = courseAttendanceService.getCourseAttendanceById(id);
        if (courseAttendance == null)
            throw new NoSuchElementException(String.format("CourseAttendance=%s not found", id));

        List<Image> imagesList = new LinkedList<>();
        imagesList = imageService.getImagesByCourseAttendance(courseAttendance.getId());

        System.out.println("imagesList: "+imagesList);

        for(Image image : imagesList) {
            image.setUrl("/picture/"+image.getId());
            image.setThumbnailUrl("/thumbnail/"+image.getId());
            image.setDeleteUrl("/deleteImage/"+image.getId());
            image.setDeleteType("DELETE");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("files", imagesList);
        map.put("form", courseAttendance);

        return new ModelAndView("courseattendance/courseattendance_upload", map);
    }

    @PreAuthorize("hasAnyAuthority('GIAO_VIEN','ADMIN')")
    @RequestMapping(value = "/{id}/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map handleUploadImages(@RequestParam("files") MultipartFile[] files, @ModelAttribute("form") CourseAttendance form) {
        List<Image> list = new LinkedList<>();
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                MultipartFile mpf = files[i];

                String newFilenameBase = UUID.randomUUID().toString();
                String originalFileExtension = mpf.getOriginalFilename().substring(mpf.getOriginalFilename().lastIndexOf("."));
                String newFilename = newFilenameBase + originalFileExtension;

                String storageDirectory = fileUploadDirectory;
                String contentType = mpf.getContentType();

                File newFile = new File(storageDirectory + newFilename);
                try {
                    mpf.transferTo(newFile);

                    BufferedImage thumbnail = Scalr.resize(ImageIO.read(newFile), 290);
                    String thumbnailFilename = newFilenameBase + "-thumbnail.png";
                    File thumbnailFile = new File(storageDirectory + "/" + thumbnailFilename);
                    ImageIO.write(thumbnail, "png", thumbnailFile);

                    Image image = new Image();
                    image.setName(mpf.getOriginalFilename());
                    image.setNewFilename(newFilename);
                    image.setSize(mpf.getSize());
                    image.setStatus(ModelStatus.AP_DUNG);
                    image.setCourseAttendance(form);
                    image.setThumbnailFilename(thumbnailFilename);
                    image.setThumbnailSize(thumbnailFile.length());
                    image.setContentType(contentType);

                    Calendar cal = Calendar.getInstance();
                    image.setDateCreated(cal.getTime());
                    image.setLastUpdated(cal.getTime());

                    imageService.create(image);

                    image.setThumbnailUrl("/thumbnail/"+image.getId());
                    image.setUrl("/picture/"+image.getId());
                    image.setDeleteUrl("/deleteImage/"+image.getId());
                    image.setDeleteType("DELETE");

                    list.add(image);
                } catch (Exception e) {
                    LOGGER.error("Could not upload file " + mpf.getOriginalFilename(), e);
                }
            }
        } else {
            LOGGER.error("Unable to upload. File is empty.");
        }

        Map<String, Object> mfiles = new HashMap<>();
        mfiles.put("files", list);
        return mfiles;
    }

    @PreAuthorize("hasAnyAuthority('GIAO_VIEN','ADMIN')")
    @RequestMapping(value = "/{id}/attendance", method = RequestMethod.GET)
    public ModelAndView attendance(@PathVariable("id") long id) {
        CourseAttendance courseAttendance = courseAttendanceService.getCourseAttendanceById(id);
        if (courseAttendance == null)
            throw new NoSuchElementException(String.format("CourseAttendance=%s not found", id));

        course = courseService.getCourseById(courseAttendance.getCourse().getId());
        List <User> sinhVienList = new ArrayList<>();
        for(User u : userService.getAllUsersInCourse(course.getId())) {
            if (u.getRole() == Role.USER) {
                sinhVienList.add(u);
            }
        }

        List<Image> imagesList = new ArrayList<>();
        imagesList = imageService.getImagesByCourseAttendance(courseAttendance.getId());
        List<String> s = new ArrayList<>();

        try{
            for (int i = 0; i < imagesList.size(); i++) {
                Image image = imagesList.get(i);
                File file = new File(fileUploadDirectory + image.getNewFilename());
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
        model.addAttribute("course", course);
        return new ModelAndView("courseattendance/courseattendance_attendance", "model", model);
    }
}
