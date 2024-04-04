package Progetto.BoatsItalia.BoatsItalia.controller;

import Progetto.BoatsItalia.BoatsItalia.model.entities.Advertisement;
import Progetto.BoatsItalia.BoatsItalia.service.AdvertisementService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200",maxAge = 3600 )
@RequestMapping("/announcements")
public class AdvertisementController {
    @Autowired
    private AdvertisementService advertisementService;


    @GetMapping("")
    public ResponseEntity<List<Advertisement>> getAnnouncementById() {
        List<Advertisement> advertisements = advertisementService.getAllAnnouncements();
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


    @PostMapping("/search")
    public  ResponseEntity<?> searchByTitle(@RequestBody String title) throws JsonProcessingException {
        System.out.println(title);
        List<Advertisement> advertisements = advertisementService.searchByTitle(title);
        ObjectMapper mapper = new ObjectMapper();

        System.out.println(mapper.writeValueAsString(advertisements.get(0)));
        return ResponseEntity.ok(advertisements);
    }
}
