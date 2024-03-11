package Progetto.BoatsItalia.BoatsItalia.repository;

import Progetto.BoatsItalia.BoatsItalia.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    List<Announcement> findByPrice(Double price);

    List<Announcement> findByRegion(String region);
}
