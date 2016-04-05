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
    public User create(User user) {
        User userCreated = new User();
        userCreated.setMa(user.getMa());
        userCreated.setLastName(user.getLastName());
        userCreated.setFirstName(user.getFirstName());
        userCreated.setEmail(user.getEmail());
        userCreated.setPasswordHash(new BCryptPasswordEncoder().encode(user.getPasswordHash()));
        userCreated.setRole(user.getRole());
        return userRepository.save(userCreated);
    }

    @Override
    public User update(User user) {
        User userUpdated = getUserById(user.getId());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(!encoder.matches(user.getPasswordHash(), userUpdated.getPasswordHash())){
            user.setPasswordHash(new BCryptPasswordEncoder().encode(user.getPasswordHash()));
        }
        return userRepository.save(user);
    }

    @Override
    public boolean isFieldUnique(String s, Long id) {
        User entity = getUserByMa(s);
        return (entity == null || (id != null && entity.getId() == id));
    }

    @Override
    public void delete(long id) {
        userRepository.delete(id);
    }

    @Override
    public List<User> getAllUsersInCourse(long courseId) {
        return userRepository.findAllInCourse(courseId);
    }

}
