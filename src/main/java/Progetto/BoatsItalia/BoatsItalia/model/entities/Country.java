package Progetto.BoatsItalia.BoatsItalia.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Country {
@NotBlank
     private String name="Italy";
     @Id
     @GeneratedValue (strategy = GenerationType.IDENTITY)
     private Long id;

     @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
        private List<Region> regions;

        public Country() {
        }

           public Country(String name) {
                this.name = name;
            }




}
