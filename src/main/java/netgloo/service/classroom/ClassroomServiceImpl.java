package netgloo.service.classroom;

import netgloo.domain.Classroom;
import netgloo.repository.ClassroomRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by G551 on 12/14/2015.
 */
@Service
public class ClassroomServiceImpl implements ClassroomService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassroomServiceImpl.class);
    private final ClassroomRepository classroomRepository;

    @Autowired
    public ClassroomServiceImpl(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }

    @Override
    public Classroom getClassroomById(long id) {
        LOGGER.debug("Getting class={}", id);
        LOGGER.info("Getting class={} " + id);
        return classroomRepository.findOne(id);
    }

    @Override
    public List<Classroom> getAllClassroom() {
        LOGGER.debug("Getting all users");
        LOGGER.info("Getting all users");
        return classroomRepository.findAll(new Sort("ma"));
    }

    @Override
    public Classroom create(Classroom classroom) {
        Classroom classroomCreated = new Classroom();
        classroomCreated.setMa(classroom.getMa());
        classroomCreated.setName(classroom.getName());
        return classroomRepository.save(classroomCreated);
    }

    @Override
    public Classroom update(Classroom classroom) {
        return classroomRepository.save(classroom);
    }
}
