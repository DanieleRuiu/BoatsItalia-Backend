package Progetto.BoatsItalia.BoatsItalia.service;

import Progetto.BoatsItalia.BoatsItalia.model.Image;
import Progetto.BoatsItalia.BoatsItalia.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public void saveImage(Image image) {
        imageRepository.save(image);
    }

    public Image getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    public void updateImage(Image image) {
        imageRepository.save(image);
    }

    public void deleteImage(Long imageId) {
        imageRepository.deleteById(imageId);
    }
}