package netgloo.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by G551 on 03/21/2016.
 */

@Entity
@Table(name = "course_attendance")
public class CourseAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="course_Id", nullable=false)
    private Course course;

    @OneToMany(mappedBy = "courseAttendanceId", cascade = CascadeType.ALL)
    private Set<Image> images;

    public CourseAttendance () {

    }

    public CourseAttendance (Course course) {
        this.course = course;
    }

    @Transient
    public boolean isNew() {
        return (this.id == null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }
}
