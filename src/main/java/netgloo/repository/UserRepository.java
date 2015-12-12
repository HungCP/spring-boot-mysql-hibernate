package netgloo.repository;

import netgloo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByEmail(String email);
    List<User> findById(long id);
}
