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

    private String thumbnailFilename;
    private String newFilename;
    private String contentType;

    @Column(name = "size")
    private Long size;

    private Long thumbnailSize;

    @Column(name = "dateCreated", nullable = false)
    private Date dateCreated;

    @Column(name = "lastUpdated", nullable = false)
    private Date lastUpdated;

    @Lob
    @Column(name = "data",nullable = false)
    private byte[] data;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ModelStatus status;

    @Transient
    private String url;

    @Transient
    private String thumbnailUrl;

    @Transient
    private String deleteUrl;

    @Transient
    private String deleteType;

    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="courseAttendance_Id", nullable=false)
    private CourseAttendance courseAttendanceId;*/

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

    public String getThumbnailFilename() {
        return thumbnailFilename;
    }

    public void setThumbnailFilename(String thumbnailFilename) {
        this.thumbnailFilename = thumbnailFilename;
    }

    public String getNewFilename() {
        return newFilename;
    }

    public void setNewFilename(String newFilename) {
        this.newFilename = newFilename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getThumbnailSize() {
        return thumbnailSize;
    }

    public void setThumbnailSize(Long thumbnailSize) {
        this.thumbnailSize = thumbnailSize;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDeleteUrl() {
        return deleteUrl;
    }

    public void setDeleteUrl(String deleteUrl) {
        this.deleteUrl = deleteUrl;
    }

    public String getDeleteType() {
        return deleteType;
    }

    public void setDeleteType(String deleteType) {
        this.deleteType = deleteType;
    }

    /*public CourseAttendance getCourseAttendanceId() {
        return courseAttendanceId;
    }

    public void setCourseAttendanceId(CourseAttendance courseAttendanceId) {
        this.courseAttendanceId = courseAttendanceId;
    }*/

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public ModelStatus getStatus() {
        return status;
    }

    public void setStatus(ModelStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Image{" + "name=" + name + ", thumbnailFilename=" + thumbnailFilename + ", newFilename=" + newFilename + ", contentType=" + contentType + ", url=" + url + ", thumbnailUrl=" + thumbnailUrl + ", deleteUrl=" + deleteUrl + ", deleteType=" + deleteType + '}';
    }
}
