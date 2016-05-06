package netgloo.controllers.user;

import netgloo.domain.User;
import netgloo.domain.validator.UserCreateFormValidator;
import netgloo.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.NoSuchElementException;

@Controller
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final UserCreateFormValidator userCreateFormValidator;

    @Autowired
    public UserController(UserService userService, UserCreateFormValidator userCreateFormValidator) {
        this.userService = userService;
        this.userCreateFormValidator = userCreateFormValidator;
    }

    @InitBinder("userForm")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping("/user/{id}")
    public ModelAndView getUserPage(@PathVariable Long id) {
        LOGGER.debug("Getting user page for user={}", id);
        User user = userService.getUserById(id);
        if (user == null) throw new NoSuchElementException(String.format("User=%s not found", id));
        return new ModelAndView("user", "user", user);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public ModelAndView getUserCreatePage() {
        LOGGER.debug("Getting user create form");
        return new ModelAndView("user/user_create", "userForm", new User());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String handleUserCreateForm(@Validated @ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        LOGGER.info("Processing user create form={}, bindingResult={}", userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "user/user_create";
        }
        if(!userService.isFieldUnique(userForm.getMa(), userForm.getId())) {
            bindingResult.rejectValue("ma", "exists.userform.ma");
            return "user/user_create";
        }
        if(!userService.isFieldUnique(userForm.getEmail(), userForm.getId())) {
            bindingResult.rejectValue("email","exists.userform.email");
            return "user/user_create";
        }
        userService.create(userForm);
        return "redirect:/users";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/{id}/update", method = RequestMethod.GET)
    public ModelAndView updateUser(@PathVariable("id") long id) {
        User user = userService.getUserById(id);
        user.setConfirmPassword(user.getPasswordHash());
        if (user == null) throw new NoSuchElementException(String.format("User=%s not found", id));
        return new ModelAndView("user/user_create", "userForm", user);
    }

    @RequestMapping(value = "/user/{id}/view", method = RequestMethod.GET)
    public ModelAndView viewUser(@PathVariable("id") long id) {
        User user = userService.getUserById(id);
        user.setConfirmPassword(user.getPasswordHash());
        if (user == null) throw new NoSuchElementException(String.format("User=%s not found", id));
        return new ModelAndView("user/user_view", "userForm", user);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/{id}/update", method = RequestMethod.POST)
    public String handleUpdateUser(@Validated @ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        LOGGER.info("Processing user create form={}, bindingResult={}", userForm, bindingResult);
        if (bindingResult.hasErrors()) {
            return "user/user_create";
        }
        if(!userService.isFieldUnique(userForm.getMa(), userForm.getId())) {
            bindingResult.rejectValue("ma", "exists.userform.ma");
            return "user/user_create";
        }
        if(!userService.isFieldUnique(userForm.getEmail(), userForm.getId())) {
            bindingResult.rejectValue("email","exists.userform.email");
            return "user/user_create";
        }
        userService.update(userForm);
        return "redirect:/users";
    }

    // delete user
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/{id}/delete")
    public String handleDeleteUser(@PathVariable("id") long id) {
        LOGGER.info("Processing delete user id = ", id);
        userService.delete(id);
        return "redirect:/users";
    }

}
