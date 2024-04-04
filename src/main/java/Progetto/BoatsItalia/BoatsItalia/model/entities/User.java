package Progetto.BoatsItalia.BoatsItalia.model.entities;

import Progetto.BoatsItalia.BoatsItalia.model.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (nullable = false, unique = true)
    private String username;

    @Column (nullable = false)
    private String firstName;

    @Column (nullable = false)
    private String lastName;

    @Column (nullable = false)
    private String password;

    @Column (nullable = false, unique = true)
    private String email;

    @Column (nullable = false)
    private String role;

    private String token;

   /* @OneToMany(mappedBy = "creator", cascade = CascadeType.ALL)
    private List<Advertisement> advertisements;
*/
    public User() {
    }

    public User(String username, String password, String firstName, String lastName, String email, String role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
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
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                '}';
    }
    @JsonIgnore
    public String getHashPassword() {
        return password;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority[] UserRole = new GrantedAuthority[0];
        return List.of(UserRole);
    }

    public void setHashPassword(String encode) {
        this.password = encode;

    }
}
