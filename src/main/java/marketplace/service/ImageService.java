package marketplace.service;

import marketplace.entity.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    public Image saveImage(MultipartFile file);
    public Image getImage(Long fileId);
}
