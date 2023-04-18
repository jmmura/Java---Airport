package be.technifutur.java.airport.model.dto;

import be.technifutur.java.airport.model.entity.Pilot;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PilotDTO {

    private Long id;

    private String name;

    private PlaneDTO.CompanyDTO company;

    public static PilotDTO from(Pilot p){
        if(p==null){return null;}
        return PilotDTO.builder()
                .id(p.getId())
                .name(p.getFirstName()+" "+p.
                        getLastName())
                .company(
                        PlaneDTO.CompanyDTO.builder()
                                .id(p.getCompany().getId())
                                .name(p.getCompany().getName())
                                .originCountry(p.getCompany().getOriginCountry())
                                .build()
                )
                .build();
    }

}
