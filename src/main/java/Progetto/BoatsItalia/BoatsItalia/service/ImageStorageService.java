package Progetto.BoatsItalia.BoatsItalia.service;

//import org.springframework.stereotype.Service;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.UUID;
//
//@Service
//public class ImageStorageService {
//
//    // Directory di archiviazione delle immagini
//    private static final String STORAGE_DIRECTORY = "/path/to/image/storage/";
//
//    // Metodo per salvare un'immagine sul server
//    public String saveImage(byte[] imageData) throws IOException {
//        // Genera un nome di file univoco per l'immagine
//        String fileName = generateFileName();
//        File file = new File(STORAGE_DIRECTORY + fileName);
//        // Scrivi i dati dell'immagine nel file
//        try (FileOutputStream fos = new FileOutputStream(file)) {
//            fos.write(imageData);
//        }
//        return fileName; // Restituisce il nome del file salvato
//    }
//
//    // Metodo per caricare un'immagine dal server
//    public byte[] loadImage(String fileName) throws IOException {
//        File file = new File(STORAGE_DIRECTORY + fileName);
//        byte[] imageData = new byte[(int) file.length()];
//        // Leggi i dati dell'immagine dal file
//        try (FileInputStream fis = new FileInputStream(file)) {
//            fis.read(imageData);
//        }
//        return imageData; // Restituisce i dati dell'immagine
//    }
//
//    // Metodo per eliminare un'immagine dal server
//    public boolean deleteImage(String fileName) {
//        File file = new File(STORAGE_DIRECTORY + fileName);
//        return file.delete(); // Restituisce true se l'eliminazione ha avuto successo, altrimenti false
//    }
//
//    // Metodo per generare un nome di file univoco utilizzando UUID
//    private String generateFileName() {
//        return UUID.randomUUID().toString() + ".jpg"; // Genera un nome di file univoco
//    }
//}
