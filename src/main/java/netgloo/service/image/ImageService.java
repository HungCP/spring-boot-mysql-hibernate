package netgloo.service.image;

import netgloo.domain.Image;

import java.util.List;

/**
 * Created by G551 on 12/14/2015.
 */
public interface ImageService {

    Image getImageById(long id);

    Image getImageByName(String name);

    Image create(Image image);

    void delete(Image image);

    List<Image> getImagesByCourseAttendance(long courseAttendanceId);
}
