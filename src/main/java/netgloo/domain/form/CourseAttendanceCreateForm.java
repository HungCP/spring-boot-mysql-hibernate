package netgloo.domain.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by G551 on 03/22/2016.
 */
public class CourseAttendanceCreateForm {

    @NotEmpty
    private String name = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CourseAttendanceCreateForm{" +
                "name = " + name +
                '}';
    }
}
