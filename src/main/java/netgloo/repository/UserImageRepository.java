package netgloo.repository;

import netgloo.domain.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by G551 on 12/14/2015.
 */
public interface UserImageRepository extends JpaRepository<UserImage, Long> {

    @Query("SELECT a FROM  UserImage a WHERE a.image.id =:imageId")
    List<UserImage> findAllByImageId(@Param("imageId")long imageId);

}
