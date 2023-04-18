package be.technifutur.java.airport.repository;

import be.technifutur.java.airport.model.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport,Long> {
}
