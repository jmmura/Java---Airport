package be.technifutur.java.airport.service;

import be.technifutur.java.airport.exceptions.*;
import be.technifutur.java.airport.model.dto.*;
import be.technifutur.java.airport.model.entity.*;
import be.technifutur.java.airport.model.form.*;
import be.technifutur.java.airport.repository.*;
import be.technifutur.java.airport.service.*;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class FlightService{

    private final FlightRepository flightRepository;
    private final PlaneRepository planeRepository;
    private final AirportRepository airportRepository;
    private final PilotRepository pilotRepository;

    public FlightService(FlightRepository flightRepository, PlaneRepository planeRepository, AirportRepository airportRepository, PilotRepository pilotRepository) {
        this.flightRepository = flightRepository;
        this.planeRepository = planeRepository;
        this.airportRepository = airportRepository;
        this.pilotRepository = pilotRepository;
    }


    public FlightDTO createFlight(FlightInsertForm form) {


        // mapping
        Flight flight = form.toEntity();
        flight.setDeparture(
                airportRepository.findById(form.getStartAirportId())
                        .orElseThrow( ReferrencedResourceNotFoundException::new )
        );
        flight.setDestination(
                airportRepository.findById(form.getEndAirportId())
                        .orElseThrow( ReferrencedResourceNotFoundException::new )
        );

        flight.setCaptain(
                pilotRepository.findById(form.getCaptainId())
                        .orElseThrow( ReferrencedResourceNotFoundException::new )
        );
        flight.setFirstOfficer(
                pilotRepository.findById(form.getFirstOfficerId())
                        .orElseThrow( ReferrencedResourceNotFoundException::new )
        );

        // Les pilotes doivent être de la bonne company
        if(
                !Objects.equals(flight.getFirstOfficer().getCompany().getId(), form.getCompanyId())
                        || !Objects.equals(flight.getCaptain().getCompany().getId(), form.getCompanyId())
        )
            throw new FormValidationException("pilots should be from the same company as the plane");

        // Les airports doivent être compatible au type d'avion
        if(
                flight.getDeparture().getPlaneTypesAllowed().stream().noneMatch(type -> type.getId().equals(form.getTypePlaneId()) ) ||
                        flight.getDestination().getPlaneTypesAllowed().stream().noneMatch(type -> type.getId().equals(form.getTypePlaneId()) )
        )
            throw new FormValidationException("airport should be compatible with the desired plane type");

        // Récupération des avions disponibles
        List<Plane> availablePlane = planeRepository.findAvailablePlane(
                form.getCompanyId(),
                form.getTypePlaneId(),
                form.getDeparture(),
                form.getArrival()
        );
//        List<Plane> availablePlane = planeRepository.findByCompany_IdAndType_IdAndInMaintenanceFalse(form.getCompanyId(), form.getTypePlaneId()).stream()
//                // On filtre les avions dispo
//                .filter( plane -> plane.getFlights().stream()
//                        .allMatch(f -> TimeTools.checkNoConflict(
//                                TimeRange.of( f.getDepartureTime(), f.getArrivalTime() ),
//                                TimeRange.of( form.getDateDeparture(), form.getDateArrival() )
//                        ))
//                )
//                .toList();
        if( availablePlane.isEmpty() )
            throw new NoAvailablePlaneException();


        // get pertinent plane
        Plane planeForFlight = availablePlane.stream()
                .filter(plane ->{
                    Optional<Airport> lastDestination = plane.getFlights().stream()
                            .filter(f -> f.getArrivesAt().isBefore(flight.getDepartsAt()))
                            .max(Comparator.comparing(Flight::getArrivesAt))
                            .map(Flight::getDestination);

                    return lastDestination.isPresent() && lastDestination.get().equals(flight.getDeparture());
                })
                .findAny()
                .orElseGet( ()-> availablePlane.get( new Random().nextInt(availablePlane.size()) ) );

        flight.setPlane( planeForFlight );
        Flight entity = flightRepository.save(flight);

        return FlightDTO.from( entity );
    }

    public void update(Long id, LocalDateTime newStart, LocalDateTime newFinish){
        Flight f = flightRepository.findById(id).orElse(null);
        assert f != null;
        f.setDepartsAt(newStart);
        f.setArrivesAt(newFinish);
        flightRepository.save(f);
    }

    public void delete(Long id){
        flightRepository.deleteById(id);
    }

    public List<FlightDTO> getAll(){
        return flightRepository.findAll().stream().map(FlightDTO::from).toList();
    }
}