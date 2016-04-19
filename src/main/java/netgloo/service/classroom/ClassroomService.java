package netgloo.service.classroom;

import netgloo.domain.Classroom;

import java.util.List;

/**
 * Created by G551 on 12/14/2015.
 */
public interface ClassroomService {

    Classroom getClassroomById(long id);

    List<Classroom> getAllClassroom();

    Classroom create(Classroom classroom);

    Classroom update(Classroom classroom);

}
