package Progetto.BoatsItalia.BoatsItalia.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "announcement_id", nullable = false)
    private Announcement announcement;

    public Image() {
    }

    public Image(String fileName, String imageUrl, Announcement announcement) {
        this.fileName = fileName;
        this.imageUrl = imageUrl;
        this.announcement = announcement;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", announcement=" + announcement +
                '}';
    }
}
