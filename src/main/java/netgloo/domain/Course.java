package netgloo.domain;

import netgloo.domain.EnumStatus.CourseStatus;

import javax.persistence.*;

/**
 * Created by G551 on 12/14/2015.
 */
@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "ma", nullable = false, unique = true)
    private String ma;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "course_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CourseStatus getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(CourseStatus courseStatus) {
        this.courseStatus = courseStatus;
    }
}
