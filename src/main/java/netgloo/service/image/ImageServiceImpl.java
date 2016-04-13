package netgloo.service.image;

import netgloo.domain.Image;
import netgloo.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by G551 on 04/13/2016.
 */

@Service
public class ImageServiceImpl implements ImageService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageServiceImpl.class);
    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public List<Image> list() {
        return imageRepository.findAll();
    }

    @Override
    public Image getImageById(long id) {
        return imageRepository.findOne(id);
    }

    @Override
    public Image create(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public void delete(long id) {
        imageRepository.delete(id);
    }
}
