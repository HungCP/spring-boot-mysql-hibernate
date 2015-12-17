package netgloo.repository;

import netgloo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByEmail(String email);

    @Query("SELECT a FROM  User a, UserCourse b WHERE a.id=b.id AND b.course.id =:courseId")
    List<User> findAllInCourse(@Param("courseId")long courseId);

}
