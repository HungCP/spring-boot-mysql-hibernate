package netgloo.service.opencv;

import org.opencv.core.Core;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by G551 on 04/26/2016.
 */
@Service
public class OpencvServiceImp implements OpencvService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OpencvServiceImp.class);

    @Override
    public void initialize() {
        // Try to load the native SimpleITK library from java.library.path
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

}
