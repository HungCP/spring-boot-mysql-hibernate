package netgloo.service.attendance;

import netgloo.domain.Attendance;
import netgloo.domain.CourseAttendance;
import netgloo.domain.User;
import netgloo.repository.AttendanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by G551 on 04/28/2016.
 */
@Service
public class AttendanceServiceImp implements AttendanceService{

    private static final Logger LOGGER = LoggerFactory.getLogger(AttendanceServiceImp.class);
    private final AttendanceRepository attendanceRepository;

    @Autowired
    public AttendanceServiceImp(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public Attendance create(Attendance attendance) {
        return attendanceRepository.save(attendance);
    }

    @Override
    public Attendance getAttendanceByUserAndCourseAttendance(User user, CourseAttendance courseAttendance) {
        return attendanceRepository.findOneByUserAndCourseAttendance(user,courseAttendance);
    }
}
