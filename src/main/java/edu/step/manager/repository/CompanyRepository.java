package edu.step.manager.repository;

import edu.step.manager.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    // findAll, findById, save, delete
}
