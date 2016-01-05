package netgloo.repository;

import netgloo.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by G551 on 12/14/2015.
 */
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT a FROM  Course a, UserCourse b WHERE b.user.id =:userId AND a.id=b.course.id")
    List<Course> findMyCourse(@Param("userId")long userId);

}
