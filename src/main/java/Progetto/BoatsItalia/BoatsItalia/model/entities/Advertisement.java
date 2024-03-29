package Progetto.BoatsItalia.BoatsItalia.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data // Annotazione di Lombok per generare automaticamente i metodi getter, setter, equals, hashCode e toString
@Entity // Indica che questa classe rappresenta un'entità gestita dal framework JPA
public class Advertisement {
    @Id // Indica che questo campo è l'identificatore primario dell'entità
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera automaticamente il valore dell'ID utilizzando l'auto-incremento del database
    private int id; // Campo per l'identificatore dell'annuncio

    @Column(nullable = false, unique = true) // Indica che questo campo non può essere nullo e deve essere univoco
    private String title; // Campo per il titolo dell'annuncio

    @Column(nullable = false, length = 1000) // Indica che questo campo non può essere nullo e ha una lunghezza massima di 1000 caratteri
    private String description; // Campo per la descrizione dell'annuncio

    @Column(nullable = false) // Indica che questo campo non può essere nullo
    private double price; // Campo per il prezzo dell'annuncio

    /*@Column(nullable = false) // Indica che questo campo non può essere nullo
    private LocalDateTime publicationDate; // Campo per la data di pubblicazione dell'annuncio
*/
    @Column(nullable = false) // Indica che questo campo non può essere nullo
    private String region; // Campo per la regione dell'annuncio

  /*  @ManyToOne // Indica una relazione many-to-one con l'entità User
    @JoinColumn(name = "user_id", nullable = false) // Specifica la colonna nel database che rappresenta questa relazione
    private User creator; // Campo per l'utente creatore dell'annuncio

    @OneToMany(mappedBy = "advertisement", cascade = CascadeType.ALL) // Indica una relazione one-to-many con l'entità Image
    private List<Image> images; // Campo per le immagini associate all'annuncio
*/
    //@ManyToOne // Indica una relazione many-to-one con l'entità Category
    //@JoinColumn(name = "category_id", nullable = false) // Specifica la colonna nel database che rappresenta questa relazione
  @Column(nullable = false)
  private String category; // Campo per la categoria dell'annuncio

    @Column(nullable = false)
    private String telephone;
    @Column(nullable = false)
    private String email;
    // Costruttore vuoto necessario per JPA
    public Advertisement() {
    }

    // Costruttore con parametri per creare un nuovo annuncio
   /* public Advertisement(String title, String description, double price, LocalDateTime publicationDate, String region, User creator, Category category) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.publicationDate = publicationDate;
        this.region = region;
        this.creator = creator;
        this.category = category;
    }*/

    // Override del metodo toString per rappresentare l'oggetto Advertisement come stringa
    @Override
    public String toString() {
        return "Advertisement{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                //", publicationDate=" + publicationDate +
                ", region='" + region + '\'' +
                //", creator=" + creator +
                ", category=" + category +
                '}';
    }
}
