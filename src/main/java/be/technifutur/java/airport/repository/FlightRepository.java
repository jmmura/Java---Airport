package be.technifutur.java.airport.repository;

import be.technifutur.java.airport.model.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight,Long> {
}
