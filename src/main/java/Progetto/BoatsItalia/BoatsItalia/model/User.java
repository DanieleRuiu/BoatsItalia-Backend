package Progetto.BoatsItalia.BoatsItalia.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (nullable = false, unique = true)
    private String username;

    @Column (nullable = false, unique = true)
    private String password;

    @Column (nullable = false, unique = true)
    private String email;

    @Column (nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private List<Announcement> announcements;

    public User() {
    }

    public User(String username, String password, String email, UserRole role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
