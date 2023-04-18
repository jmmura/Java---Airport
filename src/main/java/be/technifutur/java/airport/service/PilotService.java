package be.technifutur.java.airport.service;

import be.technifutur.java.airport.model.dto.PilotDTO;
import be.technifutur.java.airport.model.entity.Pilot;
import be.technifutur.java.airport.repository.PilotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PilotService {

    private final PilotRepository pilotRepository;

    public PilotService(PilotRepository pilotRepository) {
        this.pilotRepository = pilotRepository;
    }

    public List<PilotDTO> getAll() {

        List<Pilot> pilots = pilotRepository.findAll();

        return pilots.stream()
                .map(
                        entity -> PilotDTO.builder()
                                .id(entity.getId())
                                .name(entity.getFirstName() + " " + entity.getLastName())
                                .build())
                .toList();
    }
}
