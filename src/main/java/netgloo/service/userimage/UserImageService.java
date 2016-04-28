package netgloo.service.userimage;

import netgloo.domain.UserImage;

/**
 * Created by G551 on 12/14/2015.
 */
public interface UserImageService {

    UserImage create(UserImage userImage);

    void delete(UserImage userImage);

}
