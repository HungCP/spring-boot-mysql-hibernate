package netgloo.service.courseattendance;

import netgloo.domain.CourseAttendance;

import java.util.List;

/**
 * Created by G551 on 03/21/2016.
 */
public interface CourseAttendanceService {

    List<CourseAttendance> getAllCourseAttendanceInCourse(long courseId);

}
