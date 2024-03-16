package Progetto.BoatsItalia.BoatsItalia.repository;

import Progetto.BoatsItalia.BoatsItalia.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u.email FROM User u")
    Collection<String> getAllEmails();

    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findById(@Param("id") Long id);
       User findByUsername(String username);

    Optional<Object> findByEmail(String email);




}
