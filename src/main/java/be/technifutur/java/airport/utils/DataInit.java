package be.technifutur.java.airport.utils;

import be.technifutur.java.airport.model.entity.*;
import be.technifutur.java.airport.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataInit implements InitializingBean {

    private final CompanyRepository companyRepository;
    private final TypePlaneRepository typePlaneRepository;

    private final PilotRepository pilotRepository;

    private final AirportRepository airportRepository;

    public DataInit(CompanyRepository companyRepository, TypePlaneRepository typePlaneRepository, PilotRepository pilotRepository, AirportRepository airportRepository) {
        this.companyRepository = companyRepository;
        this.typePlaneRepository = typePlaneRepository;
        this.airportRepository = airportRepository;
        this.pilotRepository = pilotRepository;
    }

    @PersistenceContext
    private EntityManager manager;

    @Override
    //@Transactional  //evite de faire les transaction.begin()
    public void afterPropertiesSet() throws  Exception{


        TypePlane type = new TypePlane();
        type.setName("big_plane");
        type.setCapacity(300);

        typePlaneRepository.save(type);

        type= new TypePlane();
        type.setName("average_plane");
        type.setCapacity(200);

        typePlaneRepository.save(type);

        type= new TypePlane();
        type.setName("small_plane");
        type.setCapacity(100);

        typePlaneRepository.save(type);

        Company company = new Company();
        company.setName("big-company");
        company.setOriginCountry("USA");

        companyRepository.save(company);

        company = new Company();
        company.setName("mid-company");
        company.setOriginCountry("BE");

        companyRepository.save(company);

        Pilot pilot = new Pilot();
        pilot.setCompany(company);
        pilot.setFirstName("prenom");
        pilot.setLastName("nom");
        pilot.setLicenseAcquisition(LocalDate.now());
        pilot.setLicenseId("ds5f45s4d");
        pilot.setAddress("adresse pilot");
        pilot.setEmail("email");
        pilot.setPhone("sdf-54689787987-ds");

        pilotRepository.save(pilot);

        pilot = new Pilot();
        pilot.setCompany(company);
        pilot.setFirstName("prenom2");
        pilot.setLastName("nom2");
        pilot.setLicenseAcquisition(LocalDate.now());
        pilot.setLicenseId("ds5f45s4d2");
        pilot.setAddress("adresse pilot2");
        pilot.setEmail("email2");
        pilot.setPhone("sdf-54689787987-ds2");

        pilotRepository.save(pilot);

        Airport airport = new Airport();
        airport.setName("Nom aeroport");
        airport.setAddress("adresse airport");
        airport.setCity("Liege");
        airport.setCountry("Belgium");
        airport.setPlaneParking(50);
        airport.getPlaneTypesAllowed().add(type);


        airportRepository.save(airport);

        airport = new Airport();
        airport.setName("Nom aeroport2");
        airport.setAddress("adresse airport2");
        airport.setCity("Liege2");
        airport.setCountry("Belgium2");
        airport.setPlaneParking(502);
        airport.getPlaneTypesAllowed().add(type);


        airportRepository.save(airport);

    }


}
