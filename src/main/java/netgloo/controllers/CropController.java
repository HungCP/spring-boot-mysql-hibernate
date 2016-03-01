package netgloo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by G551 on 03/01/2016.
 */

@Controller
public class CropController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CropController.class);

    @RequestMapping(value = "/crop", method = RequestMethod.POST)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.print("Cropppppppppppppppppppppppppppp");

        String basePath = request.getServletContext().getRealPath("/");
        int cropX=0,cropY=0,cropW=0,cropH=0;
        cropX= Integer.parseInt(request.getParameter("cropX"));
        cropY= Integer.parseInt(request.getParameter("cropY"));
        cropW= Integer.parseInt(request.getParameter("cropW"));
        cropH= Integer.parseInt(request.getParameter("cropH"));

        System.out.println(basePath+"image/baby.jpg");

        File file =new File(basePath+"image/baby.jpg");
        BufferedImage outImage=ImageIO.read(file);
        int type = outImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : outImage.getType();
        BufferedImage cropImage=outImage.getSubimage(cropX, cropY,cropW, cropH );

        String outputPath="image/"+(new Date()).getTime()+"baby.jpg";

        File cropfile = new File(basePath+outputPath);

        ImageIO.write(cropImage, "jpg",cropfile);

        PrintWriter out =response.getWriter();

        out.write("<img src='"+outputPath+"'  height=200 border=1 />");

        out.close();
    }
}
