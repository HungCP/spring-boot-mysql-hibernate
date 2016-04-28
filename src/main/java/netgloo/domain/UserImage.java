package netgloo.domain;

import javax.persistence.*;

/**
 * Created by G551 on 04/28/2016.
 */
@Entity
@Table(name = "user_images")
public class UserImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="image_id", nullable=false)
    private Image image;

    @Column(name = "xper", nullable = false)
    private int xper;

    @Column(name = "yper", nullable = false)
    private int yper;

    @Column(name = "height", nullable = false)
    private int height;

    @Column(name = "width", nullable = false)
    private int width;

    public UserImage() {
    }

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

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getXper() {
        return xper;
    }

    public void setXper(int xper) {
        this.xper = xper;
    }

    public int getYper() {
        return yper;
    }

    public void setYper(int yper) {
        this.yper = yper;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
