package netgloo.controllers.courseattendance;

import netgloo.controllers.ImageHelper;
import netgloo.domain.*;
import netgloo.domain.EnumStatus.AttendanceStatus;
import netgloo.domain.EnumStatus.ModelStatus;
import netgloo.domain.EnumStatus.Role;
import netgloo.domain.validator.CourseAttendanceCreateFormValidator;
import netgloo.service.attendance.AttendanceService;
import netgloo.service.classroom.ClassroomService;
import netgloo.service.course.CourseService;
import netgloo.service.courseattendance.CourseAttendanceService;
import netgloo.service.image.ImageService;
import netgloo.service.opencv.OpencvService;
import netgloo.service.user.UserService;
import netgloo.service.userimage.UserImageService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.imgscalr.Scalr;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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
    private final UserImageService userImageService;
    private final AttendanceService attendanceService;
    private final CourseAttendanceCreateFormValidator courseAttendanceCreateFormValidator;
    private final OpencvService opencvService;

    private String fileUploadDirectory = "D:/image/test/";
    private Course course;

    @Autowired
    public CourseAttendanceController(CourseAttendanceService courseAttendanceService, CourseService courseService, ClassroomService classroomService,
                                      UserService userService, ImageService imageService, UserImageService userImageService, AttendanceService attendanceService,
                                      OpencvService opencvService, CourseAttendanceCreateFormValidator courseAttendanceCreateFormValidator) {
        this.courseAttendanceService = courseAttendanceService;
        this.courseService = courseService;
        this.classroomService = classroomService;
        this.userService = userService;
        this.imageService = imageService;
        this.userImageService = userImageService;
        this.attendanceService = attendanceService;
        this.opencvService = opencvService;
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
        CourseAttendance courseAttendance = courseAttendanceService.create(form);

        List<User> usersInCourse = userService.getAllUsersInCourse(course.getId());
        for(int i = 0; i < usersInCourse.size(); i++) {
            User u = usersInCourse.get(i);
            if(u.getRole() == Role.USER) {
                Attendance a =  new Attendance();
                a.setUser(u);
                a.setCourseAttendance(courseAttendance);
                a.setAttendanceStatus(AttendanceStatus.VANG);
                attendanceService.create(a);
            }
        }

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

    @PreAuthorize("hasAnyAuthority('GIAO_VIEN','ADMIN')")
    @RequestMapping(value = "/{id}/upload", method = RequestMethod.GET)
    public @ResponseBody ModelAndView uploadImages(@PathVariable("id") long id) {
        CourseAttendance courseAttendance = courseAttendanceService.getCourseAttendanceById(id);
        if (courseAttendance == null)
            throw new NoSuchElementException(String.format("CourseAttendance=%s not found", id));

        List<Image> imagesList = imageService.getImagesByCourseAttendance(courseAttendance.getId());

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

        List<UserImage> userImagesList = new ArrayList<>();

        Map<String, String> map = new HashMap();
        try{
            for (int i = 0; i < imagesList.size(); i++) {
                Image image = imagesList.get(i);

                List<UserImage> userImages  = userImageService.getAllByImage(image.getId());

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

                userImagesList.addAll(userImages);
                map.put(encodedString,image.getNewFilename());
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        Collections.sort(userImagesList, new Comparator<UserImage>() {
            @Override
            public int compare(UserImage userImage1, UserImage userImage2)
            {

                return  userImage1.getUser().getMa().compareTo(userImage2.getUser().getMa());
            }
        });

        ModelMap model = new ModelMap();
        model.addAttribute("imagesList", map);
        model.addAttribute("userImagesList", userImagesList);
        model.addAttribute("sinhVienList", sinhVienList);
        model.addAttribute("course", course);
        return new ModelAndView("courseattendance/courseattendance_attendance", "model", model);
    }

    @RequestMapping(value = "/{id}/crop", method = RequestMethod.POST, consumes = "application/json")
    public String crop(@PathVariable("id") long id, @RequestBody Map<String, Object> param, HttpServletRequest request) throws ServletException, IOException{
        CourseAttendance courseAttendance = courseAttendanceService.getCourseAttendanceById(id);
        if (courseAttendance == null)
            throw new NoSuchElementException(String.format("CourseAttendance=%s not found", id));

        course = courseService.getCourseById(courseAttendance.getCourse().getId());

        if (param == null || param.isEmpty()) {
            LOGGER.error("crop - called with no parameters");
        }

        System.out.println("crop ");
        String basePath = request.getServletContext().getRealPath("/");

        int cropX = 0, cropY = 0, cropW = 0, cropH = 0;
        cropX = Integer.valueOf((String) param.get("cropX"));
        cropY = Integer.valueOf((String) param.get("cropY"));
        cropW = Integer.valueOf((String) param.get("cropW"));
        cropH = Integer.valueOf((String) param.get("cropH"));

        String imageName = (String) param.get("ImageName");
        User userSelected = userService.getUserById(Long.valueOf((String) param.get("userID")));
        Image imageSelected = imageService.getImageByName(imageName);

        Attendance attendance = attendanceService.getAttendanceByUserAndCourseAttendance(userSelected,courseAttendance);
        attendance.setAttendanceStatus(AttendanceStatus.THAM_GIA);
        attendanceService.update(attendance);

        UserImage userImage = new UserImage();
        userImage.setUser(userSelected);
        userImage.setImage(imageSelected);
        userImage.setXper(cropX);
        userImage.setYper(cropX);
        userImage.setWidth(cropW);
        userImage.setHeight(cropH);
        userImageService.create(userImage);

        File file = new File(fileUploadDirectory + imageName);
        BufferedImage outImage = ImageIO.read(file);
        BufferedImage cropImage = outImage.getSubimage(cropX, cropY, cropW, cropH);

        String outputPath = "image/" + (new Date()).getTime()+ imageName + ".jpg";
        File cropfile = new File(basePath + outputPath);
        ImageIO.write(cropImage, "jpg", cropfile);

        //Faded
        String outImageName = userSelected.getMa() + "_" + userSelected.getName() + "_" +  UUID.randomUUID().toString() + ".jpg";
        ImageHelper.writeGrayScaleImage(outImageName, ImageHelper.covertImageToGray(cropImage));

        //ImageHelper.readImageMatrix(ImageHelper.covertImageToGray(cropImage));

        //ImageHelper.readRGBImageMatrix(ImageHelper.covertImageToGray(cropImage));

        //ImageHelper.readRGBImageMatrix(cropImage);

        System.out.println("cropfile "+cropfile);
        return outputPath;
    }

    @RequestMapping(value = "/{id}/test", method = RequestMethod.GET)
    public ModelAndView attendanceFace(@PathVariable("id") long id) {
        CourseAttendance courseAttendance = courseAttendanceService.getCourseAttendanceById(id);
        if (courseAttendance == null)
            throw new NoSuchElementException(String.format("CourseAttendance=%s not found", id));

        List<Image> imagesList = new ArrayList<>();
        imagesList = imageService.getImagesByCourseAttendance(courseAttendance.getId());

        opencvService.initialize();

        Map<String, String> map = new HashMap();
        try{
            for (int i = 0; i < imagesList.size(); i++) {

                String s = getClass().getResource("/haarcascade_frontalface_alt.xml").getPath().substring(1);

                System.out.println("s: " + s);

                CascadeClassifier faceDetector = new CascadeClassifier(s);

                System.out.println("faceDetector: " + faceDetector);

                Image ima = imagesList.get(i);

                Mat image =  Imgcodecs.imread(fileUploadDirectory + ima.getNewFilename());

                MatOfRect faceDetections = new MatOfRect();
                System.out.println("faceDetections: " + faceDetections);
                System.out.println("image: " + image);
                faceDetector.detectMultiScale(image, faceDetections);
                System.out.println(String.format("Detectedaces", faceDetections.toArray().length));
                for (Rect rect : faceDetections.toArray()) {
                    Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
                }

                String filename = fileUploadDirectory + "result_" +  ima.getNewFilename();

                Imgcodecs.imwrite(filename, image);

                File file = new File(fileUploadDirectory + "result_" +  ima.getNewFilename());

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

                map.put(encodedString,fileUploadDirectory + "result_" +  ima.getNewFilename());
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        ModelMap model = new ModelMap();
        model.addAttribute("imagesFaceList", map);
        return new ModelAndView("courseattendance/courseattendance_test", "model", model);
    }

}
