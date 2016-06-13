package netgloo.controllers.image;

import netgloo.domain.Image;
import netgloo.service.image.ImageService;
import org.apache.commons.io.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by G551 on 04/22/2016.
 */

@Controller
public class ImageController {

    private final ImageService imageService;

    private String fileUploadDirectory = "E:/opt";

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @RequestMapping(value = "/thumbnail/{id}", method = RequestMethod.GET)
    public void thumbnail(HttpServletResponse response, @PathVariable Long id) {
        Image image = imageService.getImageById(id);
        File imageFile = new File(fileUploadDirectory+"/thumbnail/"+image.getThumbnailFilename());
        response.setContentType(image.getContentType());
        response.setContentLength(image.getThumbnailSize().intValue());
        try {
            InputStream is = new FileInputStream(imageFile);
            IOUtils.copy(is, response.getOutputStream());
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @RequestMapping(value = "/picture/{id}", method = RequestMethod.GET)
    public void picture(HttpServletResponse response, @PathVariable Long id) {
        Image image = imageService.getImageById(id);
        File imageFile = new File(fileUploadDirectory+"/course_attendance/"+image.getNewFilename());
        response.setContentType(image.getContentType());
        response.setContentLength(image.getSize().intValue());
        try {
            InputStream is = new FileInputStream(imageFile);
            IOUtils.copy(is, response.getOutputStream());
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @RequestMapping(value = "/deleteImage/{id}", method = RequestMethod.DELETE)
    public @ResponseBody List delete(@PathVariable Long id) {
        Image image = imageService.getImageById(id);
        File imageFile = new File(fileUploadDirectory+"/"+image.getNewFilename());
        imageFile.delete();
        File thumbnailFile = new File(fileUploadDirectory+"/"+image.getThumbnailFilename());
        thumbnailFile.delete();
        imageService.delete(image);
        List<Map<String, Object>> results = new ArrayList<>();
        Map<String, Object> success = new HashMap<>();
        success.put("success", true);
        results.add(success);
        return results;
    }

}
