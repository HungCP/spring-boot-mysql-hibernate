package netgloo.service.user;

import netgloo.domain.User;
import netgloo.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(long id) {
        LOGGER.debug("Getting user={}", id);
        return userRepository.findOne(id);
    }

    @Override
    public User getUserByMa(String ma) {
        LOGGER.debug("Getting user by ma");
        return userRepository.findOneByMa(ma);
    }

    @Override
    public User getUserByEmail(String email) {
        LOGGER.debug("Getting user by email={}", email.replaceFirst("@.*", "@***"));
        return userRepository.findOneByEmail(email);
    }

    @Override
    public Collection<User> getAllUsers() {
        LOGGER.debug("Getting all users");
        return userRepository.findAll(new Sort("role"));
    }

    @Override
    public User create(User form) {
        User user = new User();
        user.setMa(form.getMa());
        user.setLastName(form.getLastName());
        user.setFirstName(form.getFirstName());
        user.setEmail(form.getEmail());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPasswordHash()));
        user.setRole(form.getRole());
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsersInCourse(long courseId) {
        return userRepository.findAllInCourse(courseId);
    }

}
