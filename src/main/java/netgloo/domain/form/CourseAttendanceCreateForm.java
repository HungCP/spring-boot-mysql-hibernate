package netgloo.domain.form;

import netgloo.domain.Course;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 * Created by G551 on 03/22/2016.
 */
public class CourseAttendanceCreateForm {

    @NotEmpty
    private String name = "";

    private Course course;

    @Transient
    private String error = "";

    public CourseAttendanceCreateForm() {
    }

    public CourseAttendanceCreateForm(Course course) {
        this.course = course;
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

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "CourseAttendanceCreateForm{" +
                "name = " + name +
                ", course = " + course +
                '}';
    }

}
