package netgloo.service.image;

import netgloo.domain.Image;

/**
 * Created by G551 on 12/14/2015.
 */
public interface ImageService {

    Image getImageById(long id);

    Image create(Image image);

}
