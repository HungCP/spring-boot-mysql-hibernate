package netgloo.domain;

import netgloo.domain.EnumStatus.PictureType;
import netgloo.domain.EnumStatus.Role;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by G551 on 01/08/2016.
 */

@Entity
@Table(name = "picture")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "pixel_vector", nullable = false)
    @Type(type="text")
    private String pixelVector;

    @Column(name = "loai", nullable = false)
    @Enumerated(EnumType.STRING)
    private PictureType type;

    @Column(name = "date", nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="courseAttendance_Id", nullable=true)
    private CourseAttendance courseAttendance;

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

    public String getPixelVector() {
        return pixelVector;
    }

    public void setPixelVector(String pixelVector) {
        this.pixelVector = pixelVector;
    }

    public PictureType getType() {
        return type;
    }

    public void setType(PictureType type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public CourseAttendance getCourseAttendance() {
        return courseAttendance;
    }

    public void setCourseAttendance(CourseAttendance courseAttendance) {
        this.courseAttendance = courseAttendance;
    }

}
