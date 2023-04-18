package be.technifutur.java.airport.model.dto;

import be.technifutur.java.airport.model.entity.Airport;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AirportDTO {
    private String name;

    private Long id;

    public static AirportDTO from(Airport a){
        if(a==null){return null;}

        return AirportDTO.builder()
                .name(a.getName())
                .id(a.getId())
                .build();
    }
}
