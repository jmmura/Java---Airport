package be.technifutur.java.airport.repository;

import be.technifutur.java.airport.model.entity.Pilot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PilotRepository extends JpaRepository<Pilot,Long> {

}
