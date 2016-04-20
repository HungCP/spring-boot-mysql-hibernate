package netgloo.service.courseattendance;

import netgloo.domain.CourseAttendance;
import netgloo.repository.CourseAttendanceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by G551 on 03/21/2016.
 */

@Service
public class CourseAttendanceServiceImpl implements CourseAttendanceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseAttendanceServiceImpl.class);
    private final CourseAttendanceRepository courseAttendanceRepository;

    @Autowired
    public CourseAttendanceServiceImpl(CourseAttendanceRepository courseAttendanceRepository) {
        this.courseAttendanceRepository = courseAttendanceRepository;
    }

    @Override
    public CourseAttendance getCourseAttendanceById(long id) {
        LOGGER.debug("Getting courseAttendance={}", id);
        LOGGER.info("Getting courseAttendance={} " + id);
        return courseAttendanceRepository.findOne(id);
    }

    @Override
    public CourseAttendance create(CourseAttendance form) {
        CourseAttendance courseAttendance = new CourseAttendance();
        courseAttendance.setName(form.getName());
        courseAttendance.setCourse(form.getCourse());
        courseAttendance.setClassroom(form.getClassroom());
        return courseAttendanceRepository.save(courseAttendance);
    }

    @Override
    public CourseAttendance update(CourseAttendance courseAttendance) {
        System.out.println("courseAttendance course: "+courseAttendance.getCourse().getName());
        return courseAttendanceRepository.save(courseAttendance);
    }

    @Override
    public List<CourseAttendance> getAllCourseAttendanceInCourse(long courseId) {
            return courseAttendanceRepository.findAllCourseAttendanceInCourse(courseId);
    }
}
