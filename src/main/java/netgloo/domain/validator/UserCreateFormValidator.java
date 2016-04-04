package netgloo.domain.validator;

import netgloo.domain.User;
import netgloo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserCreateFormValidator implements Validator {

    @Autowired
    @Qualifier("emailValidator")
    EmailValidator emailValidator;

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ma", "NotEmpty.userForm.ma");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.userForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.userForm.lastName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty.userForm.firstName");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordHash","NotEmpty.userForm.passwordHash");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "NotEmpty.userForm.confirmPassword");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "role", "NotEmpty.userForm.role");

        if(!user.getEmail().isEmpty() && !emailValidator.valid(user.getEmail())){
            errors.rejectValue("email", "Pattern.userForm.email");
        }

        if (!user.getPasswordHash().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "Diff.userform.confirmPassword");
        }

        if (userService.getUserByMa(user.getMa()) != null) {
            errors.rejectValue("ma", "exists.userform.ma");
        }

        if (userService.getUserByEmail(user.getEmail()) != null) {
            errors.rejectValue("email", "exists.userform.email");
        }

    }
}
