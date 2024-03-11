package Progetto.BoatsItalia.BoatsItalia.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, length = 1000)
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private LocalDateTime publicationDate;

    @Column(nullable = false)
    private String region;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User creator;

    @OneToMany(mappedBy = "announcement", cascade = CascadeType.ALL)
    private List<Image> images;

    public Announcement() {
    }

    public Announcement(String title, String description, double price, LocalDateTime publicationDate, String region, User creator) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.publicationDate = publicationDate;
        this.region = region;
        this.creator = creator;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", publicationDate=" + publicationDate +
                ", region='" + region + '\'' +
                ", creator=" + creator +
                '}';
    }
}
