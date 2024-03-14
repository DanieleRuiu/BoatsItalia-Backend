package Progetto.BoatsItalia.BoatsItalia.model.entities;

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
    @Lob
    private byte[] blob;


    @ManyToOne
    @JoinColumn(name = "advertisement_id", nullable = false)
    private Advertisement advertisement;

    public Image() {
    }

    public Image(String fileName, byte[] blob, Advertisement advertisement) {
        this.fileName = fileName;
        this.blob = blob;
        this.advertisement = advertisement;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", advertisement=" + advertisement +
                '}';
    }
}
