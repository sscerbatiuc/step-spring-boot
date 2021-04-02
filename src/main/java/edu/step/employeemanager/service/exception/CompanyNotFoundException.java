package edu.step.employeemanager.service.exception;

public class CompanyNotFoundException extends EntityNotFoundException {

    public CompanyNotFoundException(Integer id) {
        super("Company", id);
    }
}
