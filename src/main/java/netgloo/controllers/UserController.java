package netgloo.controllers;

import netgloo.models.User;
import netgloo.models.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserDao _userDao;

    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(long id) {
        try {
            User user = new User(id);
            _userDao.delete(user);
        } catch (Exception ex) {
            return ex.getMessage();
        }
        return "User succesfully deleted!";
    }

    @RequestMapping(value = "/get-by-email")
    @ResponseBody
    public String getByEmail(String email) {
        String userId;
        try {
            User user = _userDao.getByEmail(email);
            userId = String.valueOf(user.getId());
        } catch (Exception ex) {
            return "User not found";
        }
        return "The user id is: " + userId;
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public String create(String email, String name, String password, User.Role role) {
        try {
            User user = new User(email, name, password, role);
            _userDao.save(user);
        } catch (Exception ex) {
            return ex.getMessage();
        }
        return "User succesfully saved!";
    }

} // class UserController
