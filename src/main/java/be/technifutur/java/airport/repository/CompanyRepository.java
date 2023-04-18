package be.technifutur.java.airport.repository;

import be.technifutur.java.airport.model.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Long> {

}
