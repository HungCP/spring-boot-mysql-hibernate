package netgloo.service.user;

import netgloo.domain.User;
import netgloo.domain.UserCreateForm;
import netgloo.domain.Picture;

import java.util.Collection;
import java.util.List;

public interface UserService {

    User getUserById(long id);

    User getUserByMa(String ma);

    User getUserByEmail(String email);

    Collection<User> getAllUsers();

    User create(UserCreateForm form);

    List<User> getAllUsersInCourse(long courseId);

    List<Picture> findPicturesByUser(long userId);
}
