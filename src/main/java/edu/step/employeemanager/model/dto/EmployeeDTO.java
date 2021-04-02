package edu.step.employeemanager.model.dto;

import edu.step.employeemanager.model.Employee;

public class EmployeeDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String idnp;
    private Integer company;
    private Double salary;

    public EmployeeDTO() {
    }

    public EmployeeDTO(Integer id, String firstName, String lastName, String idnp, Double salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idnp = idnp;
        this.salary = salary;
    }


    public static EmployeeDTO from(Employee employee) {
        final EmployeeDTO employeeDTO = new EmployeeDTO(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getIdnp(), employee.getSalary());
        if(employee.getCompany() != null){
            employeeDTO.setCompany(employee.getCompany().getId());
        }
        return employeeDTO;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdnp() {
        return idnp;
    }

    public void setIdnp(String idnp) {
        this.idnp = idnp;
    }

    public Integer getCompany() {
        return company;
    }

    public void setCompany(Integer company) {
        this.company = company;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
