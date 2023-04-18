package be.technifutur.java.airport.repository;

import be.technifutur.java.airport.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import  java.util.*;

public interface UserRepository extends JpaRepository<User,Long> {

    @Query("SELECT user FROM User user WHERE user.username = :username")  //:username est l'argument de la fonction
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

}
