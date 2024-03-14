package Progetto.BoatsItalia.BoatsItalia.repository;

import Progetto.BoatsItalia.BoatsItalia.model.entities.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    List<Advertisement> findByPrice(Double price);

    List<Advertisement> findByRegion(String region);
}
