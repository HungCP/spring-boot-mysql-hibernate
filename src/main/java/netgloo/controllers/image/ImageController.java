package netgloo.controllers.image;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by G551 on 04/22/2016.
 */

@Controller
@RequestMapping("/viewImages")
public class ImageController {

    @RequestMapping(value = "/{imageId}")
    @ResponseBody
    public ModelAndView viewImage(@PathVariable long imageId)  {
        List<Integer> lst = Arrays.asList(1, 2);
        //File file = new File("D:/image/test/test.jpg");
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

        return new ModelAndView("test", "imagesList", s);
    }
}
