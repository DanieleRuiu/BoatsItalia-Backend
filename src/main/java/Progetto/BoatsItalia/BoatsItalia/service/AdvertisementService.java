package Progetto.BoatsItalia.BoatsItalia.service;

import Progetto.BoatsItalia.BoatsItalia.model.entities.Advertisement;
import Progetto.BoatsItalia.BoatsItalia.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.query.EscapeCharacter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertisementService {
    @Autowired
    private AdvertisementRepository advertisementRepository;

    public void createAnnouncement(Advertisement advertisement){
        advertisementRepository.save(advertisement);
    }

    public void updateAnnouncement(Advertisement advertisement){
        advertisementRepository.save(advertisement);
    }

    public void deleteAnnouncement(Advertisement advertisement){
        advertisementRepository.delete(advertisement);
    }

    public Advertisement getAnnouncementById(Long id){
        return advertisementRepository.findById(id).orElse(null);
    }

    public List<Advertisement> getAllAnnouncements(){
        return advertisementRepository.findAll();
    }

    public List<Advertisement> getAnnouncementsByPrice(Double price){
        return advertisementRepository.findByPrice(price);
    }


    public List <Advertisement> getAnnouncementsByRegion(String region){
        return advertisementRepository.findByRegion(region);
    }

    public void updateAnnouncement(Long id, Advertisement advertisement){
        advertisementRepository.save(advertisement);
    }

    public void deleteAnnouncement(Long id){
        advertisementRepository.deleteById(id);
    }

    public List <Advertisement> searchByTitle(String title){
        //String searchKey = EscapeCharacter.DEFAULT.escape(title);
        return advertisementRepository.findByTitleIgnoreCase(title);
    }
}
