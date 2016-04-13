package netgloo.repository;

import netgloo.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by G551 on 04/13/2016.
 */
public interface ImageRepository extends JpaRepository<Image, Long> {

    /*@Query("SELECT a FROM  Image a, Course b WHERE a.course.id =:courseId AND a.course.id=b.id")
    List<Image> findImagesByCourse(@Param("courseId")long courseId);*/

}
