package Progetto.BoatsItalia.BoatsItalia.controller;

import Progetto.BoatsItalia.BoatsItalia.model.entities.Image;
import Progetto.BoatsItalia.BoatsItalia.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController // Indica che questa classe è un controller REST
@RequestMapping("/api/images") // Mappatura di base per le richieste HTTP in questo controller
public class ImageController {
    @Autowired
    private ImageService imageService; // Iniezione di dipendenza del servizio ImageService

    @PostMapping // Mappatura per le richieste POST su /api/images
    public ResponseEntity<?> saveImage(@RequestBody Image image) {
        imageService.saveImage(image); // Chiama il metodo del servizio per salvare un'immagine
        return ResponseEntity.ok().build(); // Restituisce una risposta HTTP 200 OK senza contenuto
    }

    @GetMapping("/{id}") // Mappatura per le richieste GET su /api/images/{id}
    public ResponseEntity<Image> getImageById(@PathVariable Long id) {
        Image image = imageService.getImageById(id); // Ottiene un'immagine per l'ID specificato
        if (image != null) {
            return ResponseEntity.ok(image); // Restituisce l'immagine trovata con una risposta HTTP 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Restituisce una risposta HTTP 404 Not Found se l'immagine non è stata trovata
        }
    }

    @PutMapping("/{id}") // Mappatura per le richieste PUT su /api/images/{id}
    public ResponseEntity<?> updateImage(@PathVariable int id, @RequestBody Image image) {
        image.setId(id); // Imposta l'ID dell'immagine con quello fornito nella richiesta
        imageService.updateImage(image); // Chiama il metodo del servizio per aggiornare l'immagine
        return ResponseEntity.ok().build(); // Restituisce una risposta HTTP 200 OK senza contenuto
    }

    @DeleteMapping("/{id}") // Mappatura per le richieste DELETE su /api/images/{id}
    public ResponseEntity<?> deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id); // Chiama il metodo del servizio per eliminare l'immagine
        return ResponseEntity.ok().build(); // Restituisce una risposta HTTP 200 OK senza contenuto
    }
    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        return imageService.uploadImage(file);
    }
}
