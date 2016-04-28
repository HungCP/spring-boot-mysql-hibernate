package netgloo.repository;

import netgloo.domain.UserImage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by G551 on 12/14/2015.
 */
public interface UserImageRepository extends JpaRepository<UserImage, Long> {
}
