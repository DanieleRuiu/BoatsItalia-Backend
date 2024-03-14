package Progetto.BoatsItalia.BoatsItalia.configuration;

//import Progetto.BoatsItalia.BoatsItalia.model.entities.Country;
//import Progetto.BoatsItalia.BoatsItalia.repository.CountryRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AppInitializationCountry implements CommandLineRunner {
//    @Autowired
//    private CountryRepository countryRepository;
//
//   @Override
//    public void run(String... args) throws Exception {
//        // Verifica se esiste già un record per l'Italia nel database
//        if (countryRepository.findByName("Italy") == null) {
//            // Se non esiste, inserisci il record per l'Italia
//            Country italy = new Country();
//            italy.setName("Italy");
//            countryRepository.save(italy);
//        }
//    }
//}