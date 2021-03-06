package netgloo.service.course;

import netgloo.domain.Course;
import netgloo.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by G551 on 12/14/2015.
 */
@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseServiceImpl.class);
    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Course getCourseById(long id) {
        LOGGER.debug("Getting course={}", id);
        LOGGER.info("Getting course={} " + id);
        return courseRepository.findOne(id);
    }

    @Override
    public Course getCourseByMa(String ma) {
        LOGGER.debug("Getting user by ma");
        return courseRepository.findOneByMa(ma);
    }

    @Override
    public Collection<Course> getAllCourses() {
        LOGGER.debug("Getting all course");
        LOGGER.info("Getting all course");
        return courseRepository.findAll(new Sort("ma"));
    }

    @Override
    public Collection<Course> getMyCourses(long courseId) {
        LOGGER.debug("Getting my course");
        LOGGER.info("Getting my course");
        return courseRepository.findMyCourse(courseId);
    }

    @Override
    public Course create(Course course) {
        Course courseCreated = new Course();
        courseCreated.setMa(course.getMa());
        courseCreated.setName(course.getName());
        courseCreated.setCourseStatus(course.getCourseStatus());
        return courseRepository.save(courseCreated);
    }

    @Override
    public Course update(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public boolean isFieldUnique(String s, Long id) {
        Course entity = getCourseByMa(s);
        return (entity == null || (id != null && entity.getId() == id));
    }
}
