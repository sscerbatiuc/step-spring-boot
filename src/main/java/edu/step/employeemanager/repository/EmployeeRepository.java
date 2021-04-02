package edu.step.employeemanager.repository;

import edu.step.employeemanager.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    List<Employee> findEmployeesBySalaryGreaterThan(Double salary);
}
