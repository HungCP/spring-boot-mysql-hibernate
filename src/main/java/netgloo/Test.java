package netgloo;

/**
 * Created by G551 on 04/05/2016.
 */
/*
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class Test {
    // Compulsory
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    public static void main(String[] args) {
        CascadeClassifier faceDetector = new CascadeClassifier("C:\\opencv\\sources\\data\\haarcascades\\haarcascade_frontalface_alt.xml");
        System.out.println("faceDetector: "+faceDetector);
        Mat image =  Imgcodecs.imread("D:/PICTURE/image.jpg");
        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);
        System.out.println(String.format("Detectedaces", faceDetections.toArray().length));
        for (Rect rect : faceDetections.toArray()) {
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
        }
        String filename = "D:/PICTURE/result.jpg";
        System.out.println(String.format("Writing filename"));
        Imgcodecs.imwrite(filename, image);
    }
}*/
