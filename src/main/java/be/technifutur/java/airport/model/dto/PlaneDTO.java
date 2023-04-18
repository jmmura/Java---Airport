package be.technifutur.java.airport.model.dto;
import be.technifutur.java.airport.model.entity.Company;
import be.technifutur.java.airport.model.entity.Plane;
import lombok.*;

import java.time.LocalDate;
import java.util.*;

@Data
@Builder
public class PlaneDTO {

    private long id;

    private String callSign;

    private LocalDate registrationDate;

    private boolean inMaintenance;

    private TypeDTO type;

    private CompanyDTO company;

    @Data
    @Builder
    public static class TypeDTO{
        private  Long typeId;

        private String name;

        private int capacity;
    }

    @Data
    @Builder
    public static class CompanyDTO{
        private long id;
        private String name;
        private String originCountry;
    }

    public static PlaneDTO from(Plane entity){
        if(entity == null)
            return null;

        return PlaneDTO.builder()
                .id(entity.getId())
                .inMaintenance(entity.isInMaintenance())
                .callSign(entity.getCallSign())
                .registrationDate(entity.getRegistrationDate())
                .type(
                        TypeDTO.builder()
                                .typeId(entity.getType().getId())
                                .name(entity.getType().getName())
                                .capacity(entity.getType().getCapacity())
                                .build()
                )
                .company(
                        PlaneDTO.CompanyDTO.builder()
                                .id(entity.getCompany().getId())
                                .name(entity.getCompany().getName())
                                .originCountry(entity.getCompany().getOriginCountry())
                                .build()
                )
                .build();
    }

}
