package Progetto.BoatsItalia.BoatsItalia.service;

import Progetto.BoatsItalia.BoatsItalia.model.Announcement;
import Progetto.BoatsItalia.BoatsItalia.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementService {
    @Autowired
    private AnnouncementRepository announcementRepository;

    public void createAnnouncement(Announcement announcement){
        announcementRepository.save(announcement);
    }

    public void updateAnnouncement(Announcement announcement){
        announcementRepository.save(announcement);
    }

    public void deleteAnnouncement(Announcement announcement){
        announcementRepository.delete(announcement);
    }

    public Announcement getAnnouncementById(Long id){
        return announcementRepository.findById(id).orElse(null);
    }

    public List<Announcement> getAllAnnouncements(){
        return announcementRepository.findAll();
    }

    public List<Announcement> getAnnouncementsByPrice(Double price){
        return announcementRepository.findByPrice(price);
    }

    public List <Announcement> getAnnouncementsByRegion(String region){
        return announcementRepository.findByRegion(region);
    }

    public void updateAnnouncement(Long id, Announcement announcement){
        announcementRepository.save(announcement);
    }

    public void deleteAnnouncement(Long id){
        announcementRepository.deleteById(id);
    }
}
