package edu.step.employeemanager.service;

import edu.step.employeemanager.model.Company;
import edu.step.employeemanager.model.Employee;
import edu.step.employeemanager.model.dto.EmployeeDTO;
import edu.step.employeemanager.repository.CompanyRepository;
import edu.step.employeemanager.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeDTOService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyRepository companyRepository;

    public void create(EmployeeDTO dto) throws Exception {
        Employee employee = new Employee();
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setIdnp(dto.getIdnp());
        // cautam compania dupa ID
        final Company company = this.companyRepository.findById(dto.getCompany()).orElseThrow(() -> new Exception("Cannot link employee. Company does not exist."));
        // asignam compania la angajat
        employee.setCompany(company);
        Employee savedEmployee = this.employeeRepository.save(employee);

        if (company.getEmployees() == null) {
            company.setEmployees(new HashSet<>());
        }
        company.getEmployees().add(savedEmployee);
        this.companyRepository.save(company);
    }

    public List<EmployeeDTO> getAll() {
        // get all companies
        final List<Employee> all = this.employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        // convert to DTO
        List<EmployeeDTO> dtos = all.stream().map(employee -> EmployeeDTO.from(employee)
        ).collect(Collectors.toList());
        return dtos;
    }
}