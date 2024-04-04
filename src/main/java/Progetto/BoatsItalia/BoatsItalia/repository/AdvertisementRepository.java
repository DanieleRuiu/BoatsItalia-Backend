package Progetto.BoatsItalia.BoatsItalia.repository;

import Progetto.BoatsItalia.BoatsItalia.model.entities.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    List<Advertisement> findByPrice(Double price);

    @Query("SELECT a FROM Advertisement a WHERE UPPER(a.title) LIKE UPPER(CONCAT('%', :title, '%'))")
    List<Advertisement> findByTitleIgnoreCase(@Param("title") String title);
    List<Advertisement> findByRegion(String region);
}
