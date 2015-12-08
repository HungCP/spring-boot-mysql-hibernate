package netgloo.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by G551 on 12/08/2015.
 */
@Repository
@Transactional
public class ClassroomDao {

    @Autowired
    private SessionFactory _sessionFactory;

    private Session getSession() {
        return _sessionFactory.getCurrentSession();
    }

    public void save(Classroom classroom) {
        getSession().save(classroom);
        return;
    }

    public void delete(Classroom classroom) {
        getSession().delete(classroom);
        return;
    }

    @SuppressWarnings("unchecked")
    public List<User> getAll() {
        return getSession().createQuery("from Classroom").list();
    }

    public Classroom getById(long id) {
        return (Classroom) getSession().load(Classroom.class, id);
    }

    public void update(Classroom classroom) {
        getSession().update(classroom);
        return;
    }
}
