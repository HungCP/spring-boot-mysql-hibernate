package netgloo.domain.form;

import netgloo.domain.Course;
import netgloo.service.courseattendance.CourseAttendanceServiceImpl;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by G551 on 03/22/2016.
 */
public class CourseAttendanceCreateForm {

    @NotEmpty
    private String name = "";

    private Course course;

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

    @Override
    public String toString() {
        return "CourseAttendanceCreateForm{" +
                "name = " + name +
                '}';
    }
}
