package netgloo.domain.validator;

import netgloo.domain.form.UserCreateForm;
import netgloo.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserCreateFormValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCreateFormValidator.class);
    private final UserService userService;

    @Autowired
    public UserCreateFormValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        UserCreateForm form = (UserCreateForm) target;
        validateMa(errors, form);
        validateEmail(errors, form);
        validatePasswords(errors, form);
    }

    private void validateMa(Errors errors, UserCreateForm form) {
        if (userService.getUserByMa(form.getMa()) != null) {
            form.setError("Mã này đã được đăng ký.");
            errors.reject("ma.exists", "Mã này đã được đăng ký.");
        }
    }

    private void validatePasswords(Errors errors, UserCreateForm form) {
        if (!form.getPassword().equals(form.getPasswordRepeated())) {
            form.setError("Mật khẩu không trùng khớp với nhau.");
            errors.reject("password.no_match", "Passwords do not match");
        }
    }

    private void validateEmail(Errors errors, UserCreateForm form) {
        if (userService.getUserByEmail(form.getEmail()) != null) {
            form.setError("Email này đã được đăng ký.");
            errors.reject("email.exists", "Email này đã được đăng ký");
        }
    }
}
