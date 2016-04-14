package netgloo.service.classroom;

import netgloo.domain.Classroom;

import java.util.Collection;

/**
 * Created by G551 on 12/14/2015.
 */
public interface ClassroomService {

    Classroom getClassroomById(long id);

    Collection<Classroom> getAllClassroom();

    Classroom create(Classroom classroom);

    Classroom update(Classroom classroom);

}
