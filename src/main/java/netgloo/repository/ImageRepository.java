package netgloo.repository;

import netgloo.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by G551 on 12/14/2015.
 */
public interface ImageRepository extends JpaRepository<Image, Long> {

    @Query("SELECT a FROM  Image a WHERE a.courseAttendance.id =:courseAttendanceId")
    List<Image> findImagesByCourseAttendanceId(@Param("courseAttendanceId")long courseAttendanceId);
}
