package netgloo.service.attendance;

import netgloo.domain.Attendance;
import netgloo.domain.CourseAttendance;
import netgloo.domain.User;

/**
 * Created by G551 on 12/14/2015.
 */
public interface AttendanceService {

    Attendance create(Attendance attendance);

    Attendance update(Attendance attendance);

    Attendance getAttendanceByUserAndCourseAttendance(User user, CourseAttendance courseAttendance);

}
