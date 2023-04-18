package be.technifutur.java.airport.model.dto;

import be.technifutur.java.airport.model.entity.Airport;
import be.technifutur.java.airport.model.entity.Flight;
import be.technifutur.java.airport.model.entity.Pilot;
import be.technifutur.java.airport.model.entity.Plane;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class FlightDTO {

    private long id;

    private PlaneDTO plane;

    private LocalDateTime departsAt;

    private LocalDateTime arrivesAt;

    private AirportDTO departure;

    private AirportDTO destination;

    private PilotDTO captain;

    private PilotDTO firstOfficer;

    private boolean cancelled;

    public static FlightDTO from(Flight f){
        if(f==null){return null;}

        return FlightDTO.builder()
                .id(f.getId())
                .departsAt(f.getDepartsAt())
                .arrivesAt(f.getArrivesAt())
                .cancelled(f.isCancelled())
                .plane(PlaneDTO.from(f.getPlane()))
                .departure(AirportDTO.from(f.getDeparture()))
                .destination(AirportDTO.from(f.getDestination()))
                .captain(PilotDTO.from(f.getCaptain()))
                .firstOfficer(PilotDTO.from(f.getFirstOfficer()))
                .build();
    }
}
