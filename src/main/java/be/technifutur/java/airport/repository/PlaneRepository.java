package be.technifutur.java.airport.repository;

import be.technifutur.java.airport.model.entity.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface PlaneRepository extends JpaRepository<Plane,Long> {

    //UPDATE Plane p SET p.inMaintenance = ?2 WHERE p.id = ?1
    @Modifying
    @Transactional
    @Query("UPDATE Plane p SET p.inMaintenance = ?2 WHERE p.id = ?1")
    void updateMaintenance(Long id, Boolean maintenance);

    @Query("""
        SELECT p
        FROM Plane p
        WHERE
            p.company.id = ?1 AND
            p.type.id = ?2 AND
            NOT p.inMaintenance
            AND (
                SELECT count(f)
                FROM Flight f
                WHERE
                    p.id = f.plane.id AND
                    NOT ( f.arrivesAt < ?3 OR f.departsAt > ?4)
            ) = 0
    """)
    List<Plane> findAvailablePlane(Long companyId, Long typeId, LocalDateTime departure, LocalDateTime arrival);


}
