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
@RequestMapping("/admin")
public class AdvertismentUserController {
    @Autowired
    private AdvertisementService advertisementService;

    @PostMapping()
    public ResponseEntity<Advertisement> createAnnouncement(@RequestBody Advertisement advertisement) {
        advertisementService.createAnnouncement(advertisement);
        return ResponseEntity.ok().build();
    }



    @PutMapping("/{id}")
    public ResponseEntity<?> updateAnnouncement(@PathVariable int id, @RequestBody Advertisement advertisement) {
        advertisement.setId(id);
        advertisementService.updateAnnouncement(advertisement);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnnouncement(@PathVariable Long id) {
        advertisementService.deleteAnnouncement(id);
        return ResponseEntity.ok().build();
    }


}

