package Progetto.BoatsItalia.BoatsItalia.controller;

import Progetto.BoatsItalia.BoatsItalia.model.entities.Category;
import Progetto.BoatsItalia.BoatsItalia.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Indica che questa classe è un controller REST
@RequestMapping("/api/categories") // Mappatura di base per le richieste HTTP in questo controller
public class   CategoryController {

    @Autowired
    private CategoryService categoryService; // Iniezione di dipendenza del servizio CategoryService

    @GetMapping("/all") // Mappatura per le richieste GET su /api/categories/all
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories(); // Ottiene tutte le categorie dal servizio CategoryService
        return ResponseEntity.ok(categories); // Restituisce tutte le categorie con una risposta HTTP 200 OK
    }
}
