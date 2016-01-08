package netgloo.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;

/**
 * Created by G551 on 01/08/2016.
 */

@Entity
@Table(name = "picture")
public class UserPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "pixel_vector", nullable = false)
    @Type(type="text")
    private String pixelVector;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_Id", nullable=false)
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
