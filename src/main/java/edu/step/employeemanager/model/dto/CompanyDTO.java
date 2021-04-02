package edu.step.employeemanager.model.dto;

import edu.step.employeemanager.model.Company;

import java.util.List;
import java.util.stream.Collectors;

public class CompanyDTO {
    private Integer id;
    private String name;
    private List<Integer> employees;

    public CompanyDTO() {
    }

    public CompanyDTO(Integer id, String name, List<Integer> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Integer> employees) {
        this.employees = employees;
    }

    public static CompanyDTO from(Company company) {
        final CompanyDTO dto = new CompanyDTO();
        dto.setId(company.getId());
        dto.setName(company.getName());
        if (company.getEmployees() != null) {
            final List<Integer> ids = company.getEmployees().stream()
                    .map(employee -> employee.getId())
                    .collect(Collectors.toList());
            dto.setEmployees(ids);
        }
        return dto;
    }
}
