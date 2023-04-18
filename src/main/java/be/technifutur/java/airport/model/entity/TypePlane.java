package be.technifutur.java.airport.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class TypePlane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private int capacity;

    @ManyToMany(mappedBy = "planeTypesAllowed")
    private List<Airport> airports = new ArrayList<>();

    @OneToMany(mappedBy= "type")
    private List<Plane> planes = new ArrayList<>();

}
