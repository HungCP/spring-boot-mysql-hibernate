package netgloo.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by G551 on 12/08/2015.
 */
@Entity
@Table(name="classroom")
public class Classroom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min = 3, max = 80)
    private String code;

    @NotNull
    @Size(min = 3, max = 80)
    private String name;

    public Classroom() { }

    public Classroom(long id) {
        this.id = id;
    }

    public Classroom(long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
} // class Classroom
