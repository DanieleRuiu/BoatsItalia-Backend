package Progetto.BoatsItalia.BoatsItalia.controller;

import Progetto.BoatsItalia.BoatsItalia.model.entities.Advertisement;
import Progetto.BoatsItalia.BoatsItalia.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200",maxAge = 3600 )// Indica che questa classe è un controller REST
@RequestMapping("/announcements") // Mappatura di base per le richieste HTTP in questo controller
public class AdvertisementController {
    @Autowired
    private AdvertisementService advertisementService; // Iniezione di dipendenza del servizio Advertisement
    @CrossOrigin(origins="http://localhost:4200",maxAge = 3600)
    @PostMapping("") // Mappatura per le richieste POST su /api/announcements
    public ResponseEntity<Advertisement> createAnnouncement(@RequestBody Advertisement advertisement) {
        advertisementService.createAnnouncement(advertisement); // Chiama il metodo del servizio per creare un annuncio
        return ResponseEntity.ok().build(); // Restituisce una risposta HTTP 200 OK senza contenuto
    }

    @GetMapping("") // Mappatura per le richieste GET su /api/announcements/{id}
    public ResponseEntity<List<Advertisement>> getAnnouncementById() {
        List<Advertisement> advertisements = advertisementService.getAllAnnouncements(); // Ottiene un annuncio per l'ID specificato

        System.out.println("QUI");
        System.out.println(advertisements);
        if (advertisements != null) {
            return ResponseEntity.ok(advertisements); // Restituisce l'annuncio trovato con una risposta HTTP 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Restituisce una risposta HTTP 404 Not Found se l'annuncio non è stato trovato
        }
    }

    @GetMapping("/{id}") // Mappatura per le richieste GET su /api/announcements/{id}
    public ResponseEntity<Advertisement> getAnnouncementById(@PathVariable Long id) {
        Advertisement advertisement = advertisementService.getAnnouncementById(id); // Ottiene un annuncio per l'ID specificato
        if (advertisement != null) {
            return ResponseEntity.ok(advertisement); // Restituisce l'annuncio trovato con una risposta HTTP 200 OK
        } else {
            return ResponseEntity.notFound().build(); // Restituisce una risposta HTTP 404 Not Found se l'annuncio non è stato trovato
        }
    }

    @GetMapping("/region/{region}") // Mappatura per le richieste GET su /api/announcements/region/{region}
    public ResponseEntity<List<Advertisement>> getAnnouncementsByRegion(@PathVariable String region) {
        List<Advertisement> advertisements = advertisementService.getAnnouncementsByRegion(region); // Ottiene gli annunci per la regione specificata
        return ResponseEntity.ok(advertisements); // Restituisce gli annunci trovati con una risposta HTTP 200 OK
    }

    @GetMapping("/price/{price}") // Mappatura per le richieste GET su /api/announcements/price/{price}
    public ResponseEntity<List<Advertisement>> getAnnouncementsByPriceLessThanEqual(@PathVariable double price) {
        List<Advertisement> advertisements = advertisementService.getAnnouncementsByPrice(price); // Ottiene gli annunci per il prezzo specificato o inferiore
        return ResponseEntity.ok(advertisements); // Restituisce gli annunci trovati con una risposta HTTP 200 OK
    }

    @PutMapping("/{id}") // Mappatura per le richieste PUT su /api/announcements/{id}
    public ResponseEntity<?> updateAnnouncement(@PathVariable int id, @RequestBody Advertisement advertisement) {
        advertisement.setId(id); // Imposta l'ID dell'annuncio con quello fornito nella richiesta
        advertisementService.updateAnnouncement(advertisement); // Chiama il metodo del servizio per aggiornare l'annuncio
        return ResponseEntity.ok().build(); // Restituisce una risposta HTTP 200 OK senza contenuto
    }

    @DeleteMapping("/{id}") // Mappatura per le richieste DELETE su /api/announcements/{id}
    public ResponseEntity<?> deleteAnnouncement(@PathVariable Long id) {
        advertisementService.deleteAnnouncement(id); // Chiama il metodo del servizio per eliminare l'annuncio
        return ResponseEntity.ok().build(); // Restituisce una risposta HTTP 200 OK senza contenuto
    }
}
