package netgloo.controllers.courseattendance;

import netgloo.domain.Course;
import netgloo.domain.CourseAttendance;
import netgloo.domain.Image;
import netgloo.service.attendance.AttendanceService;
import netgloo.service.classroom.ClassroomService;
import netgloo.service.course.CourseService;
import netgloo.service.courseattendance.CourseAttendanceService;
import netgloo.service.image.ImageService;
import netgloo.service.opencv.OpencvService;
import netgloo.service.user.UserService;
import netgloo.service.userimage.UserImageService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by G551 on 10/05/2016.
 */
@Controller
@RequestMapping("/autoAttendance")
public class AutoAttendanceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AutoAttendanceController.class);

    private final CourseAttendanceService courseAttendanceService;
    private final OpencvService opencvService;
    private final ImageService imageService;
    private final UserService userService;


    private String fileUploadDirectory = "D:/image/test/";



    @Autowired
    public AutoAttendanceController(CourseAttendanceService courseAttendanceService, UserService userService, ImageService imageService, OpencvService opencvService) {
        this.courseAttendanceService = courseAttendanceService;
        this.userService = userService;
        this.imageService = imageService;
        this.opencvService = opencvService;
    }

    @RequestMapping(value = "/{id}/autoAttendance", method = RequestMethod.GET)
    public String autoAttendance(@PathVariable("id") long id) {
        CourseAttendance courseAttendance = courseAttendanceService.getCourseAttendanceById(id);
        if (courseAttendance == null)
            throw new NoSuchElementException(String.format("CourseAttendance=%s not found", id));


        List<Image> imagesList = new ArrayList<>();
        imagesList = imageService.getImagesByCourseAttendance(courseAttendance.getId());

        opencvService.initialize();

        for (int i = 0; i < imagesList.size(); i++) {

            String s = getClass().getResource("/haarcascade_frontalface_alt.xml").getPath().substring(1);
            CascadeClassifier faceDetector = new CascadeClassifier(s);
            Image ima = imagesList.get(i);
            Mat image =  Imgcodecs.imread(fileUploadDirectory + ima.getNewFilename());
            MatOfRect faceDetections = new MatOfRect();
            faceDetector.detectMultiScale(image, faceDetections);
            for (Rect rect : faceDetections.toArray()) {
                Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
            }

            String filename = fileUploadDirectory + "result_" +  ima.getNewFilename();
            Imgcodecs.imwrite(filename, image);
        }

        return "redirect:/course/" + courseAttendance.getCourse().getId() + "/attendance";
    }
}
