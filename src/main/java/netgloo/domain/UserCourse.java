package netgloo.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by G551 on 12/15/2015.
 */
@Entity
@Table(name = "user_course")
public class UserCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_Id", nullable=false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="course_Id", nullable=false)
    private Course course;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
