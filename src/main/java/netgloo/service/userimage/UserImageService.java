package netgloo.service.userimage;

import netgloo.domain.UserImage;

import java.util.List;

/**
 * Created by G551 on 12/14/2015.
 */
public interface UserImageService {

    UserImage getUserImageById(long id);

    UserImage create(UserImage userImage);

    void delete(UserImage userImage);

    List<UserImage> getAllByImage(long imageId);

}
