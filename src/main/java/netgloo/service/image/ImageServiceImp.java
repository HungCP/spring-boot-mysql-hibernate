package netgloo.service.image;

import netgloo.domain.Image;
import netgloo.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by G551 on 04/26/2016.
 */
@Service
public class ImageServiceImp implements ImageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImp.class);
    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImp(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image getImageById(long id) {
        LOGGER.debug("Getting class={}", id);
        LOGGER.info("Getting class={} " + id);
        return imageRepository.findOne(id);
    }

    @Override
    public Image getImageByName(String name) {
        return imageRepository.findOneByName(name);
    }

    @Override
    public Image create(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public void delete(Image image) {
        imageRepository.delete(image.getId());
    }

    @Override
    public List<Image> getImagesByCourseAttendance(long courseAttendanceId) {
        return imageRepository.findImagesByCourseAttendanceId(courseAttendanceId);
    }
}
