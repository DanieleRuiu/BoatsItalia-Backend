package Progetto.BoatsItalia.BoatsItalia.controller;

import Progetto.BoatsItalia.BoatsItalia.model.Announcement;
import Progetto.BoatsItalia.BoatsItalia.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/announcements")
public class AnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    @PostMapping
    public ResponseEntity<?> createAnnouncement(@RequestBody Announcement announcement) {
        announcementService.createAnnouncement(announcement);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Announcement> getAnnouncementById(@PathVariable Long id) {
        Announcement announcement = announcementService.getAnnouncementById(id);
        if (announcement != null) {
            return ResponseEntity.ok(announcement);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/region/{region}")
    public ResponseEntity<List<Announcement>> getAnnouncementsByRegion(@PathVariable String region) {
        List<Announcement> announcements = announcementService.getAnnouncementsByRegion(region);
        return ResponseEntity.ok(announcements);
    }

    @GetMapping("/price/{price}")
    public ResponseEntity<List<Announcement>> getAnnouncementsByPriceLessThanEqual(@PathVariable double price) {
        List<Announcement> announcements = announcementService.getAnnouncementsByPrice(price);
        return ResponseEntity.ok(announcements);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAnnouncement(@PathVariable int id, @RequestBody Announcement announcement) {
        announcement.setId(id);
        announcementService.updateAnnouncement(announcement);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnnouncement(@PathVariable Long id) {
        announcementService.deleteAnnouncement(id);
        return ResponseEntity.ok().build();
    }
}