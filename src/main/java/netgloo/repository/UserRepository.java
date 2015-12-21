package netgloo.repository;

import netgloo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByEmail(String email);

    @Query("SELECT a FROM  User a, UserCourse b WHERE b.course.id =:courseId AND a.id=b.user.id")
    List<User> findAllInCourse(@Param("courseId")long courseId);

}
