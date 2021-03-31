package edu.step.manager.model.dto;

public class EmployeeDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String idnp;
    private Integer company;


    public Integer getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setCompany(Integer companyId) {
        this.company = companyId;
    }
}
