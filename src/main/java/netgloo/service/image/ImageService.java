package netgloo.service.image;

import netgloo.domain.Image;

import java.util.List;

/**
 * Created by G551 on 04/13/2016.
 */
public interface ImageService {

    List<Image> list();

    Image getImageById(long id);

    Image create(Image image) ;

    public void delete(long id);
}
