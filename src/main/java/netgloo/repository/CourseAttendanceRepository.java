package netgloo.repository;

import netgloo.domain.CourseAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by G551 on 03/21/2016.
 */
public interface CourseAttendanceRepository extends JpaRepository<CourseAttendance, Long> {

    @Query("SELECT b FROM  Course a, CourseAttendance b WHERE b.course.id =:courseId AND a.id=b.course.id")
    List<CourseAttendance> findAllCourseAttendanceInCourse(@Param("courseId")long courseId);
}
