package edu.step.employeemanager.repository;

import edu.step.employeemanager.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    public List<Company> findDistinctByEmployeesNotEmpty();
}
