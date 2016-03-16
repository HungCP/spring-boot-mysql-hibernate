package netgloo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

/**
 * Created by G551 on 03/01/2016.
 */

@Controller
public class CropController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CropController.class);

    @ResponseBody
    @RequestMapping(value = {"/crop"}, method = RequestMethod.POST, consumes = "application/json")
    public String crop(@RequestBody Map<String, Object> param, HttpServletRequest request) throws ServletException, IOException {
        if (param == null || param.isEmpty()) {
            LOGGER.error("crop - called with no parameters");
        }

        String basePath = request.getServletContext().getRealPath("/");
        int cropX = 0, cropY = 0, cropW = 0, cropH = 0;
        cropX = Integer.valueOf((String) param.get("cropX"));
        cropY = Integer.valueOf((String) param.get("cropY"));
        cropW = Integer.valueOf((String) param.get("cropW"));
        cropH = Integer.valueOf((String) param.get("cropH"));

        System.out.println(basePath + "image/baby.jpg");

        File file = new File(basePath + "image/baby.jpg");
        BufferedImage outImage = ImageIO.read(file);
        int type = outImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : outImage.getType();
        BufferedImage cropImage = outImage.getSubimage(cropX, cropY, cropW, cropH);

        String outputPath = "image/" + (new Date()).getTime() + "baby.jpg";

        File cropfile = new File(basePath + outputPath);

        ImageIO.write(cropImage, "jpg", cropfile);

        System.out.println("cropImage: "+cropImage);
        System.out.println("outputPath: "+outputPath);
        return outputPath;
    }
}
