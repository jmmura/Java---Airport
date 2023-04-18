package be.technifutur.java.airport.mapper;

import be.technifutur.java.airport.model.dto.PlaneDTO;
import be.technifutur.java.airport.model.entity.Plane;
import be.technifutur.java.airport.model.form.PlaneInsertForm;
import org.springframework.stereotype.Service;


//@Service autre facon de mapper
public class PlaneMapper {
    public PlaneDTO toDTO(Plane entity){
        if(entity == null)
            return null;

        return PlaneDTO.builder()
                .id(entity.getId())
                .inMaintenance(entity.isInMaintenance())
                .callSign(entity.getCallSign())
                .registrationDate(entity.getRegistrationDate())
                .type(
                        PlaneDTO.TypeDTO.builder()
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

    public Plane toEntity(PlaneInsertForm form){
        Plane entity = new Plane();

        entity.setCallSign(form.getCallSign());
        entity.setRegistrationDate(form.getRegistrationDate());

        return entity;
    }

}