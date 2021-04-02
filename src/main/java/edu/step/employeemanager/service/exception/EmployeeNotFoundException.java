package edu.step.employeemanager.service.exception;

public class EmployeeNotFoundException extends EntityNotFoundException{

    public EmployeeNotFoundException(Integer id) {
        super("Employee", id);
    }
}
