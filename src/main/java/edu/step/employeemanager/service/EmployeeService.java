package edu.step.employeemanager.service;

import edu.step.employeemanager.model.Company;
import edu.step.employeemanager.model.Employee;
import edu.step.employeemanager.model.dto.EmployeeDTO;
import edu.step.employeemanager.repository.CompanyRepository;
import edu.step.employeemanager.repository.EmployeeRepository;
import edu.step.employeemanager.service.exception.CompanyNotFoundException;
import edu.step.employeemanager.service.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private CompanyRepository companyRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
    }

    public EmployeeDTO update(EmployeeDTO employeeDTO) throws EmployeeNotFoundException {
        final Employee employee = this.employeeRepository.findById(employeeDTO.getId()).orElseThrow(() -> new EmployeeNotFoundException(employeeDTO.getId()));
        employee.setFirstName(employeeDTO.getFirstName());
        final Employee savedEmployee = this.employeeRepository.save(employee);
        return EmployeeDTO.from(savedEmployee);
    }

    public EmployeeDTO findById(Integer id) throws EmployeeNotFoundException {
        final Employee company = employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        return EmployeeDTO.from(company);
    }

    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll().stream().map(company -> EmployeeDTO.from(company)).collect(Collectors.toList());
    }

    public EmployeeDTO save(EmployeeDTO employeeDTO) throws CompanyNotFoundException {
        final Employee newEmployee = new Employee();
        newEmployee.setId(employeeDTO.getId());
        newEmployee.setFirstName(employeeDTO.getFirstName());
        newEmployee.setLastName(employeeDTO.getLastName());
        newEmployee.setIdnp(employeeDTO.getIdnp());
        newEmployee.setSalary(employeeDTO.getSalary());
        Company linkedCompany = null;
        if (employeeDTO.getCompany() != null) {
            linkedCompany = this.companyRepository.findById(employeeDTO.getCompany()).orElseThrow(() -> new CompanyNotFoundException(employeeDTO.getCompany()));
        }
        newEmployee.setCompany(linkedCompany);
        final Employee savedEmployee = employeeRepository.save(newEmployee);
        if (linkedCompany.getEmployees() == null) {
            linkedCompany.setEmployees(new HashSet<>());
        }
        linkedCompany.getEmployees().add(savedEmployee);
        companyRepository.save(linkedCompany);
        return EmployeeDTO.from(savedEmployee);
    }

    public void delete(Integer id) throws EmployeeNotFoundException {
        final Employee employee = this.employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
        this.employeeRepository.delete(employee);
    }

    // CUSTOM METHODS

    public List<EmployeeDTO> getEmployeesWithSalaryAbove(Double salary) {
        return this.employeeRepository.findEmployeesBySalaryGreaterThan(salary).stream().map(employee -> EmployeeDTO.from(employee)).collect(Collectors.toList());
    }
}
