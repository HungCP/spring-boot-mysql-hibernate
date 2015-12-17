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
    public Collection<Course> getAllCourses() {
        LOGGER.debug("Getting all course");
        LOGGER.info("Getting all course");
        return courseRepository.findAll(new Sort("ma"));
    }

}
