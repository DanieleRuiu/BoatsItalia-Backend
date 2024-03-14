package Progetto.BoatsItalia.BoatsItalia.service;

import Progetto.BoatsItalia.BoatsItalia.model.entities.Country;
import Progetto.BoatsItalia.BoatsItalia.model.entities.Region;
import Progetto.BoatsItalia.BoatsItalia.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public void initializeItalianRegions() {
        // Lista predefinita di regioni italiane
        List<String> italianRegions = Arrays.asList(
                "Abruzzo", "Basilicata", "Calabria", "Campania", "Emilia-Romagna",
                "Friuli-Venezia Giulia", "Lazio", "Liguria", "Lombardia", "Marche",
                "Molise", "Piemonte", "Puglia", "Sardegna", "Sicilia", "Toscana",
                "Trentino-Alto Adige", "Umbria", "Valle d'Aosta", "Veneto"
        );

        // Inserisce le regioni italiane nel database
       String country = "Italy";
        for (String regionName : italianRegions) {
            Region region = new Region(regionName, country); // Assumi che tu abbia già un oggetto Country per l'Italia
            regionRepository.save(region);
        }
    }
}
