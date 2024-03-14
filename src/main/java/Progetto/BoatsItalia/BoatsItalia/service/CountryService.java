package Progetto.BoatsItalia.BoatsItalia.service;

import Progetto.BoatsItalia.BoatsItalia.model.entities.Country;
import Progetto.BoatsItalia.BoatsItalia.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public void initializeCountry() {
        Country italy = new Country();
        italy.setName("Italy");
        countryRepository.save(italy);
    }
}