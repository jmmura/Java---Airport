package be.technifutur.java.airport.service;

import be.technifutur.java.airport.model.dto.AirportDTO;
import be.technifutur.java.airport.model.entity.Airport;
import be.technifutur.java.airport.repository.AirportRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    public AirportService(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public List<AirportDTO> getAll(){
        List<Airport> airports = airportRepository.findAll();

        return airports.stream().map(
                entity -> AirportDTO.builder()
                        .name(entity.getName())
                        .id(entity.getId())
                        .build()
        ).toList();

    }


}
