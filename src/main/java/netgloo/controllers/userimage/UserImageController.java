package netgloo.controllers.userimage;

import netgloo.domain.UserImage;
import netgloo.service.userimage.UserImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by G551 on 12/14/2015.
 */
@Controller
@RequestMapping("/userImage")
public class UserImageController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserImageController.class);
    private final UserImageService userImageService;

    @Autowired
    public UserImageController(UserImageService userImageService) {
        this.userImageService = userImageService;
    }

    @RequestMapping(value = "/{id}/delete")
    public String handleDeleteUser(@PathVariable("id") long id) {
        LOGGER.info("Processing delete user id = ", id);
        UserImage userImage = userImageService.getUserImageById(id);
        userImageService.delete(userImage);
        return "redirect:/courseAttendance/"+userImage.getImage().getCourseAttendance().getId()+"/attendance";
    }

}
