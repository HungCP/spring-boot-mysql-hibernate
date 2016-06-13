package netgloo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import netgloo.domain.EnumStatus.ModelStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by G551 on 04/13/2016.
 */

@Entity
@Table(name = "images")
@JsonIgnoreProperties({"contentType", "dateCreated", "lastUpdated"})
public class Image implements Serializable {

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

    @Transient
    private String url;

    @Column(name = "date_created", nullable = false)
    private Date dateCreated;

    @Column(name = "last_updated", nullable = false)
    private Date lastUpdated;

    @Column(name = "thumbnail_filename", nullable = false)
    private String thumbnailFilename;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @Column(name = "thumbnail_size", nullable = false)
    private Long thumbnailSize;

    @Transient
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ModelStatus status;

    @Transient
    private String thumbnailUrl;

    @Transient
    private String deleteUrl;

    @Transient
    private String deleteType;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_attendance_Id", nullable = false)
    private CourseAttendance courseAttendance;

    public Image() {
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

    public String getThumbnailFilename() {
        return thumbnailFilename;
    }

    public void setThumbnailFilename(String thumbnailFilename) {
        this.thumbnailFilename = thumbnailFilename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getThumbnailSize() {
        return thumbnailSize;
    }

    public void setThumbnailSize(Long thumbnailSize) {
        this.thumbnailSize = thumbnailSize;
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

    public CourseAttendance getCourseAttendance() {
        return courseAttendance;
    }

    public void setCourseAttendance(CourseAttendance courseAttendance) {
        this.courseAttendance = courseAttendance;
    }

    public ModelStatus getStatus() {
        return status;
    }

    public void setStatus(ModelStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Image{" + "name=" + name + ",  newFilename=" + newFilename + ",  date_created=" + dateCreated + '}';
    }
}
