package netgloo.service.courseattendance;

import netgloo.domain.CourseAttendance;
import netgloo.domain.form.CourseAttendanceCreateForm;
import netgloo.repository.CourseAttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by G551 on 03/21/2016.
 */

@Service
public class CourseAttendanceServiceImpl implements CourseAttendanceService {

    private final CourseAttendanceRepository courseAttendanceRepository;

    @Autowired
    public CourseAttendanceServiceImpl(CourseAttendanceRepository courseAttendanceRepository) {
        this.courseAttendanceRepository = courseAttendanceRepository;
    }

    @Override
    public CourseAttendance create(CourseAttendanceCreateForm form) {
        CourseAttendance courseAttendance = new CourseAttendance();
        courseAttendance.setName(form.getName());
        courseAttendance.setCourse(form.getCourse());
        return courseAttendanceRepository.save(courseAttendance);
    }

    @Override
    public List<CourseAttendance> getAllCourseAttendanceInCourse(long courseId) {
            return courseAttendanceRepository.findAllCourseAttendanceInCourse(courseId);
    }
}
