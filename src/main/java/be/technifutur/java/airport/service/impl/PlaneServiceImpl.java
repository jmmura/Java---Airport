package be.technifutur.java.airport.service;

import be.technifutur.java.airport.exceptions.RessourceNotFoundException;
import be.technifutur.java.airport.mapper.PlaneMapper;
import be.technifutur.java.airport.model.dto.PlaneDTO;
import be.technifutur.java.airport.model.entity.Company;
import be.technifutur.java.airport.model.entity.Plane;
import be.technifutur.java.airport.model.entity.TypePlane;
import be.technifutur.java.airport.model.form.PlaneInsertForm;
import be.technifutur.java.airport.repository.CompanyRepository;
import be.technifutur.java.airport.repository.PlaneRepository;
import be.technifutur.java.airport.repository.TypePlaneRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class PlaneServiceImpl implements PlaneService {
    private final PlaneRepository planeRepository;
    private final TypePlaneRepository typePlaneRepository;
    private final CompanyRepository companyRepository;

    public PlaneServiceImpl(
            PlaneRepository planeRepository,
            TypePlaneRepository typePlaneRepository,
            CompanyRepository companyRepository) {
        this.planeRepository = planeRepository;
        this.typePlaneRepository = typePlaneRepository;
        this.companyRepository = companyRepository;
    }

    //--------------------------------------------------//
    @Override
    public void create(PlaneInsertForm form) {
        Plane plane = form.toEntity();

        TypePlane type = typePlaneRepository.findById(form.getTypeId())
                .orElseThrow(RessourceNotFoundException::new); //NORMAN DU FUTUR! C'EST LE MEME QUE AU DESSOUS! PS : TU ES CON NORMAN DU
        plane.setType(type);

        Company company = companyRepository.findById(form.getCompanyId())
                .orElseThrow(RessourceNotFoundException::new);
        plane.setCompany(company);

        planeRepository.save(plane);
    }

    @Override
    public PlaneDTO getOne(long id) {
        return planeRepository.findById(id)
                .map(PlaneDTO::from)
                .orElseThrow(RessourceNotFoundException::new);
    }

    @Override
    public List<PlaneDTO> getAll() {
        return planeRepository.findAll().stream().map(PlaneDTO::from).toList();
    }

    @Override
    public void updateInMaintenance(long id) {
        Plane p = planeRepository.findById(id).orElseThrow(RessourceNotFoundException::new);
        p.setInMaintenance(!p.isInMaintenance());
        planeRepository.save( p);
    }

    @Override
    public void updateCompany(Long id, Long companyId) {
        Company company = companyRepository
                .findById(companyId)
                .orElseThrow(RessourceNotFoundException::new);
        Plane plane = planeRepository
                .findById(id)
                .orElseThrow(RessourceNotFoundException::new);

        plane.setCompany(company);
        planeRepository.save(plane);
    }

    @Override
    public void updateMaintenance(Long id, Boolean inMaintenance) {
        planeRepository.updateMaintenance(id, inMaintenance);
    }

    @Override
    public void updateCompanyAlex(long idPlane, long companyId) {

    }

    @Override
    public void delete(long id){
        planeRepository.deleteById(id);
    }
}