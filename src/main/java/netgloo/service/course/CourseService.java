package netgloo.service.course;

import netgloo.domain.Course;

import java.util.Collection;

/**
 * Created by G551 on 12/14/2015.
 */
public interface CourseService {

    Course getCourseById(long id);

    Collection<Course> getAllCourses();

    Collection<Course> getMyCourses(long courseId);

}
