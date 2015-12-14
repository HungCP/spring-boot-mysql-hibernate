package netgloo.repository;

import netgloo.domain.Classroom;
import netgloo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

/**
 * Created by G551 on 12/14/2015.
 */
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {

}
