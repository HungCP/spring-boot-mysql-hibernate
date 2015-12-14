package netgloo.repository;

import netgloo.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by G551 on 12/14/2015.
 */
public interface CourseRepository extends JpaRepository<Course, Long> {

}
