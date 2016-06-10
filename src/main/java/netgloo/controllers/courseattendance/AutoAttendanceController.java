package netgloo.controllers.courseattendance;

import netgloo.controllers.ImageHelper;
import netgloo.domain.*;
import netgloo.domain.EnumStatus.AttendanceStatus;
import netgloo.domain.EnumStatus.Role;
import netgloo.service.attendance.AttendanceService;
import netgloo.service.courseattendance.CourseAttendanceService;
import netgloo.service.image.ImageService;
import netgloo.service.opencv.OpencvService;
import netgloo.service.user.UserService;
import netgloo.service.userimage.UserImageService;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
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
public class AutoAttendanceController{

    private static final Logger LOGGER = LoggerFactory.getLogger(AutoAttendanceController.class);

    private final CourseAttendanceService courseAttendanceService;
    private final OpencvService opencvService;
    private final ImageService imageService;
    private final UserImageService userImageService;
    private final AttendanceService attendanceService;
    private final UserService userService;

    List<Image> imagesList = new ArrayList<>();


    private String imageStoreDirectory = "D:/image/course_attendance/";
    private String faceStoreDirectory = "D:/image/face_image/";
    private String fileFaceDirectory = "D:/image/face_text/";

    @Autowired
    public AutoAttendanceController(CourseAttendanceService courseAttendanceService, UserService userService, ImageService imageService, OpencvService opencvService,
                                    UserImageService userImageService, AttendanceService attendanceService) {
        this.courseAttendanceService = courseAttendanceService;
        this.userService = userService;
        this.imageService = imageService;
        this.userImageService = userImageService;
        this.attendanceService = attendanceService;
        this.opencvService = opencvService;
    }

    public List<UserImage> getUserImageList(CourseAttendance courseAttendance){
        imagesList = imageService.getImagesByCourseAttendance(courseAttendance.getId());
        List<UserImage> userImagesList = new ArrayList<>();
        for (int i = 0; i < imagesList.size(); i++) {
            Image image = imagesList.get(i);
            List<UserImage> userImages  = userImageService.getAllByImage(image.getId());
            userImagesList.addAll(userImages);
        }
        return userImagesList;
    }

    public List<User> getSinhVienList(long id) {
        List <User> sinhVienList = new ArrayList<>();
        for(User u : userService.getAllUsersInCourse(id)) {
            if (u.getRole() == Role.USER) {
                sinhVienList.add(u);
            }
        }
        return sinhVienList;
    }

    @RequestMapping(value = "/manualAttendance/{id}", method = RequestMethod.GET)
    public String manualAttendance(@PathVariable("id") long id){
        CourseAttendance courseAttendance = courseAttendanceService.getCourseAttendanceById(id);
        if (courseAttendance == null)
            throw new NoSuchElementException(String.format("CourseAttendance=%s not found", id));

        List<UserImage> userImagesList = getUserImageList(courseAttendance);
        System.out.println("userImagesList: "+userImagesList);

        List<User> sinhVienList = getSinhVienList(courseAttendance.getCourse().getId());
        System.out.println("sinhVienList: "+sinhVienList);

        List<User> atteandanceStudents = new ArrayList<>();

        if(userImagesList != null ) {
            for(int i = 0; i <userImagesList.size(); i++){
                User u = userImagesList.get(i).getUser();
                if(!atteandanceStudents.contains(u)) {
                    atteandanceStudents.add(u);
                }
            }
        }
        System.out.println("atteandanceStudents: "+atteandanceStudents);

        for(User u : atteandanceStudents) {
            Attendance attendance = attendanceService.getAttendanceByUserAndCourseAttendance(u,courseAttendance);
            attendance.setAttendanceStatus(AttendanceStatus.THAM_GIA);
            attendanceService.update(attendance);
        }

        return "redirect:/course/" + courseAttendance.getCourse().getId() + "/attendance";
    }

    @RequestMapping(value = "/autoAttendance/{id}", method = RequestMethod.GET)
    public String autoAttendance(@PathVariable("id") long id) throws IOException {
        System.out.println("vào");
        CourseAttendance courseAttendance = courseAttendanceService.getCourseAttendanceById(id);
        if (courseAttendance == null)
            throw new NoSuchElementException(String.format("CourseAttendance=%s not found", id));

        List<Image> imagesList = new ArrayList<>();
        imagesList = imageService.getImagesByCourseAttendance(courseAttendance.getId());

        List<User> sinhVienList =  getSinhVienList(courseAttendance.getCourse().getId());

        opencvService.initialize();

        for (int i = 0; i < imagesList.size(); i++) {

            Image image = imagesList.get(i);
            String imagePath = imageStoreDirectory + image.getNewFilename();

            File file = new File(imagePath);
            BufferedImage originalImage = ImageIO.read(file);

            String s = getClass().getResource("/haarcascade_frontalface_alt.xml").getPath().substring(1);
            CascadeClassifier faceDetector = new CascadeClassifier(s);

            Mat faceImage =  Imgcodecs.imread(imagePath);

            Mat imageMat =  Imgcodecs.imread(imagePath);
            List<BufferedImage> scaleFaceImages = new ArrayList<>();
            MatOfRect faceDetections = new MatOfRect();
            faceDetector.detectMultiScale(faceImage, faceDetections);
            for (Rect rect : faceDetections.toArray()) {
                Imgproc.rectangle(imageMat, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
                BufferedImage cropFaceImage = originalImage.getSubimage(rect.x, rect.y, rect.width, rect.height);
                BufferedImage scaleFaceImage = ImageHelper.scale(cropFaceImage);
                //ImageHelper.writeImage("resultFace_" +  UUID.randomUUID().toString() + ".jpg", graysclaeFaceImage);
                scaleFaceImages.add(scaleFaceImage);

                double distance = 0;
                User seclectedUser =  new User();
                int k = 0;
                for(User u : sinhVienList) {
                    File f = new File(fileFaceDirectory+u.getMa()+"_"+u.getCount()+".txt");
                    if(f.exists() && !f.isDirectory()) {
                        double[][] averageMatrix = ImageHelper.readMatrixFromFile(f);
                        if(k == 0) {
                            distance = ImageHelper.computeImageDistanceByEuclid(averageMatrix, ImageHelper.covertImageToGrayMatrix(scaleFaceImage));
                            //distance = ImageHelper.computeImageDistanceByManhattan(averageMatrix, ImageHelper.covertImageToGrayMatrix(scaleFaceImage));
                            seclectedUser = u;
                            k++;
                        } else {
                            double temp = ImageHelper.computeImageDistanceByEuclid(averageMatrix, ImageHelper.covertImageToGrayMatrix(scaleFaceImage));
                            //double temp = ImageHelper.computeImageDistanceByManhattan(averageMatrix, ImageHelper.covertImageToGrayMatrix(scaleFaceImage));
                            if(distance > temp) {
                                distance = temp;
                                seclectedUser = u;
                            }
                        }
                    }
                }
                System.out.println("distance: "+distance);
                UserImage userImage =  new UserImage();
                userImage.setUser(seclectedUser);
                userImage.setImage(image);
                userImage.setXper(rect.x);
                userImage.setYper(rect.y);
                userImage.setHeight(rect.height);
                userImage.setWidth(rect.width);
                userImageService.create(userImage);
            }
            String filename = faceStoreDirectory + "resultFace_" +  image.getNewFilename();
            Imgcodecs.imwrite(filename, imageMat);

            System.out.println("sinhVienList: "+sinhVienList);
            System.out.println("graysclaeFaceImage: "+scaleFaceImages.size());
            System.out.println("ra");
        }
        return "redirect:/course/" + courseAttendance.getCourse().getId() + "/attendance";
    }
}
