package be.technifutur.java.airport.model.form;

import be.technifutur.java.airport.model.entity.Airport;
import be.technifutur.java.airport.model.entity.Flight;
import be.technifutur.java.airport.model.entity.TypePlane;
import be.technifutur.java.airport.repository.AirportRepository;
import be.technifutur.java.airport.repository.PilotRepository;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.aspectj.lang.annotation.After;

import java.time.LocalDateTime;

@Data
public class FlightInsertForm {

    @Future
    @NotNull
    private LocalDateTime departure;

    @Future
    private LocalDateTime arrival;

    @NotNull
    private Long startAirportId;

    @NotNull
    private Long endAirportId;

    @NotNull
    private Long captainId;

    @NotNull
    private Long firstOfficerId;

    @NotNull
    private Long typePlaneId;

    @NotNull
    private Long companyId;





    public Flight toEntity(){
        Flight f = new Flight();

        f.setDepartsAt(this.departure);
        f.setArrivesAt(this.arrival);


        return f;
    }

}

/*
* @NotNull
    @Pattern(regexp = "[A-Z]-[A-Z]{4}|[A-Z]{2}-[A-Z]{3}|N[0-9]{1,5}[A-Z]{0,2}")
    private String callSign;

    @NotNull
    @PastOrPresent
    private LocalDate registrationDate;

    @NotNull
    private Long companyId;

    @NotNull
    private Long typeId;

    public Plane toEntity(){
        Plane plane = new Plane();

        plane.setCallSign(this.callSign);
        plane.setRegistrationDate(this.getRegistrationDate());

        return plane;
    }
* */

/*
* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id",nullable = false)
    private Long id;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departsAt;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivesAt;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id", nullable = false)
    private Airport departure;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id", nullable = false)
    private Airport destination;

    @ManyToOne
    @JoinColumn(name = "plane_id", nullable = false)
    private Plane plane;

    @ManyToOne
    @JoinColumn(name = "captain_id", nullable = false)
    private Pilot captain;

    @ManyToOne
    @JoinColumn(name = "first_officer_id", nullable = false)
    private Pilot firstOfficer;

    @Column(name = "cancelled", nullable = false)
    private boolean cancelled;

    @OneToMany(mappedBy = "flight")
    private List<Booking> bookings;
* */