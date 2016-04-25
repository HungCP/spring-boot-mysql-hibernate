package netgloo.repository;

import netgloo.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by G551 on 12/14/2015.
 */
public interface ImageRepository extends JpaRepository<Image, Long> {

}
