package netgloo.service.userimage;

import netgloo.domain.UserImage;
import netgloo.repository.UserImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by G551 on 04/28/2016.
 */
@Service
public class UserImageServiceImp implements UserImageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserImageServiceImp.class);
    private final UserImageRepository userImageRepository;

    @Autowired
    public UserImageServiceImp(UserImageRepository userImageRepository) {
        this.userImageRepository = userImageRepository;
    }

    @Override
    public UserImage getUserImageById(long id) {
        return userImageRepository.findOne(id);
    }

    @Override
    public UserImage create(UserImage userImage) {
        return userImageRepository.save(userImage);
}

    @Override
    public void delete(UserImage userImage) {
        userImageRepository.delete(userImage);
    }

    @Override
    public List<UserImage> getAllByImage(long imageId) {
        return userImageRepository.findAllByImageId(imageId);
    }
}
