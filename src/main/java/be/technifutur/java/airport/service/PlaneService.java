package be.technifutur.java.airport.service;

import be.technifutur.java.airport.model.dto.PlaneDTO;
import be.technifutur.java.airport.model.entity.Plane;
import be.technifutur.java.airport.model.form.PlaneInsertForm;

import java.util.*;

public interface PlaneService {
    void create(PlaneInsertForm form);

    PlaneDTO getOne(long id);

    List<PlaneDTO> getAll();

    void updateInMaintenance(long id);

    void updateCompany(Long id, Long companyId);

    void updateMaintenance(Long id, Boolean inMaintenance);

    public void updateCompanyAlex(long idPlane,long companyId);

    public void delete(long id);



    //void updateCompany(long id);
}