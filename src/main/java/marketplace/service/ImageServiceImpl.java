package marketplace.service;

import marketplace.entity.Image;
import marketplace.repos.ImageRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class ImageServiceImpl implements ImageService{
    @Autowired
    ImageRepos imageRepos;

    @Override
    public Image saveImage(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(fileName.contains("..")) {
                throw new IOException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Image image = new Image(fileName, file.getContentType(), file.getBytes());
            return imageRepos.save(image);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public Image getImage(Long fileId) {
        return imageRepos.findById(fileId).get();
    }
}
