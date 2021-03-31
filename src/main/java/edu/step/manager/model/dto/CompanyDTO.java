package edu.step.manager.model.dto;

import javax.persistence.OneToMany;
import java.util.Set;

public class CompanyDTO { // Data Transfer Object

    private Integer id;
    private String name;
    private Set<Integer> employees;


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

    public Set<Integer> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Integer> employees) {
        this.employees = employees;
    }
}
