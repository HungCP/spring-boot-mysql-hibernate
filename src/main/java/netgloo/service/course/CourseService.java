package netgloo.service.course;

import netgloo.domain.Course;

import java.util.Collection;

/**
 * Created by G551 on 12/14/2015.
 */
public interface CourseService {

    Course getCourseById(long id);

    Course getCourseByMa(String ma);

    Collection<Course> getAllCourses();

    Collection<Course> getMyCourses(long courseId);

    Course create(Course course);

    Course update(Course course);

    boolean isFieldUnique(String s, Long id);

}
