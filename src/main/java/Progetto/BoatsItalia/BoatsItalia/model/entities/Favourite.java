package Progetto.BoatsItalia.BoatsItalia.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Favourite {

@Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long Id;
@ManyToOne
@JoinColumn(name = "user_id", nullable = false)
    private User user;

@ManyToOne
@JoinColumn(name = "advertisement_id", nullable = false)
    private Advertisement advertisement;

    public Favourite() {
    }

    public Favourite(User user, Advertisement advertisement) {
        this.user = user;
        this.advertisement = advertisement;
    }

    @Override
    public String toString() {
        return "Favourite{" +
                "id=" + Id +
                ", user=" + user +
                ", advertisement=" + advertisement +
                '}';
    }


}
