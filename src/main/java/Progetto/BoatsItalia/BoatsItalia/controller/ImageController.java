package Progetto.BoatsItalia.BoatsItalia.controller;

import Progetto.BoatsItalia.BoatsItalia.model.Image;
import Progetto.BoatsItalia.BoatsItalia.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/images")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @PostMapping
    public ResponseEntity<?> saveImage(@RequestBody Image image) {
        imageService.saveImage(image);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImageById(@PathVariable Long id) {
        Image image = imageService.getImageById(id);
        if (image != null) {
            return ResponseEntity.ok(image);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateImage(@PathVariable int id, @RequestBody Image image) {
        image.setId(id);
        imageService.updateImage(image);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return ResponseEntity.ok().build();
    }
}
