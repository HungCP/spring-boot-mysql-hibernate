package netgloo.repository;

import netgloo.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by G551 on 12/14/2015.
 */
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

}

