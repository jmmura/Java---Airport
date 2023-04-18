package be.technifutur.java.airport.repository;

import be.technifutur.java.airport.model.entity.TypePlane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypePlaneRepository extends JpaRepository<TypePlane,Long> {
}
