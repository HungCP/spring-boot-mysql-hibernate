package netgloo.service.courseattendance;

import netgloo.domain.CourseAttendance;

import java.util.List;

/**
 * Created by G551 on 03/21/2016.
 */
public interface CourseAttendanceService {

    CourseAttendance create(CourseAttendance form);

    CourseAttendance getCourseAttendanceById(long id);

    List<CourseAttendance> getAllCourseAttendanceInCourse(long courseId);

}
