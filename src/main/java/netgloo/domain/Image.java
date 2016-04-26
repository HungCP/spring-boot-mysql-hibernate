package netgloo.domain;

import netgloo.domain.EnumStatus.ModelStatus;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by G551 on 04/13/2016.
 */

@Entity
@Table(name = "images")
@JsonIgnoreProperties({"id","thumbnailFilename","newFilename","contentType","dateCreated","lastUpdated"})
public class Image implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "new_filename")
    private String newFilename;

    @Column(name = "size")
    private Long size;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "date_created", nullable = false)
    private Date dateCreated;

    @Column(name = "last_updated", nullable = false)
    private Date lastUpdated;


    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ModelStatus status;


    public Image() {}

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

    public String getNewFilename() {
        return newFilename;
    }

    public void setNewFilename(String newFilename) {
        this.newFilename = newFilename;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }


    public ModelStatus getStatus() {
        return status;
    }

    public void setStatus(ModelStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Image{" + "name=" + name + ",  newFilename=" + newFilename + '}';
    }
}
