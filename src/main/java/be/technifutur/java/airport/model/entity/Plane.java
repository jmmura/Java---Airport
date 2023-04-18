package be.technifutur.java.airport.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import be.technifutur.java.airport.constraints.registration;

@Entity
@Getter @Setter
public class Plane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plane_id", nullable = false)
    private Long id;

    @Column(name = "call_sign", nullable = false, unique = true)
    private String callSign;    //registration number

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;

    @Column(name = "in_maintenance", nullable = false)
    private boolean inMaintenance;

    @OneToMany(mappedBy = "plane")
    private List<Flight> flights;

    @ManyToOne
    @JoinColumn(name = "plane_type", nullable = false)
    private TypePlane type;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;



}
