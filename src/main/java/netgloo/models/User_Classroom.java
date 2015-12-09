package netgloo.models;

import javax.persistence.*;

/**
 * Created by G551 on 12/08/2015.
 */
@Entity
@Table(name="user_classroom")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User_Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Classroom classroom;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
