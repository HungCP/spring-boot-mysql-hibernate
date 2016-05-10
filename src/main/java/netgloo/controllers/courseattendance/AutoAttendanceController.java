package netgloo.controllers.courseattendance;

import netgloo.domain.CourseAttendance;
import netgloo.domain.Image;
import netgloo.service.courseattendance.CourseAttendanceService;
import netgloo.service.image.ImageService;
import netgloo.service.opencv.OpencvService;
import netgloo.service.user.UserService;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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


    private String imageStoreDirectory = "D:/image/course_attendance/";
    private String faceStoreDirectory = "D:/image/face_image/";



    @Autowired
    public AutoAttendanceController(CourseAttendanceService courseAttendanceService, UserService userService, ImageService imageService, OpencvService opencvService) {
        this.courseAttendanceService = courseAttendanceService;
        this.userService = userService;
        this.imageService = imageService;
        this.opencvService = opencvService;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String autoAttendance(@PathVariable("id") long id) throws IOException {
        CourseAttendance courseAttendance = courseAttendanceService.getCourseAttendanceById(id);
        if (courseAttendance == null)
            throw new NoSuchElementException(String.format("CourseAttendance=%s not found", id));


        List<Image> imagesList = new ArrayList<>();
        imagesList = imageService.getImagesByCourseAttendance(courseAttendance.getId());

        opencvService.initialize();

        for (int i = 0; i < imagesList.size(); i++) {

            Image image = imagesList.get(i);
            String imagePath = imageStoreDirectory + image.getNewFilename();

            File file = new File(imagePath);
            BufferedImage originalImage = ImageIO.read(file);

            String s = getClass().getResource("/haarcascade_frontalface_alt.xml").getPath().substring(1);
            CascadeClassifier faceDetector = new CascadeClassifier(s);

            Mat faceImage =  Imgcodecs.imread(imagePath);

            MatOfRect faceDetections = new MatOfRect();
            faceDetector.detectMultiScale(faceImage, faceDetections);
            for (Rect rect : faceDetections.toArray()) {
                BufferedImage cropFaceImage = originalImage.getSubimage(rect.x, rect.y, rect.width, rect.height);
                String result = faceStoreDirectory + "Face_" +  image.getNewFilename() + "_" + i;
                File resultFile = new File(result);
                ImageIO.write(cropFaceImage, "jpg", resultFile);
            }

        }
        return "redirect:/course/" + courseAttendance.getCourse().getId() + "/attendance";
    }
}
