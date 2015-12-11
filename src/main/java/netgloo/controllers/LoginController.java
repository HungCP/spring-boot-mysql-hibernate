package netgloo.controllers;

import netgloo.models.User;
import netgloo.models.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * Created by G551 on 12/08/2015.
 */

@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserDao _userDao;

    @RequestMapping(value = "/login")
    public String login(Map<String, Object> model, String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            log.info("error");
            model.put("error", "Email and Password is not empty!");
            return "index";
        }
        try {
            User user = _userDao.getByEmail(email);
            if (user.getPassword().equalsIgnoreCase(password)) {
                log.info("loginedUser");
                model.put("loginedUser", user);
                //return "mainPage";
                return "redirect:/mainPage";
            }
        } catch (Exception ex) {
            System.out.println("ms: "+ex.getMessage());
            //return ex.getMessage();
        }
        log.info("error");
        model.put("error", "User is invalid!");
        return "index";
    }
}
