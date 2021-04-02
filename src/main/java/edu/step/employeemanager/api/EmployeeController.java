package edu.step.employeemanager.api;

import edu.step.employeemanager.model.dto.EmployeeDTO;
import edu.step.employeemanager.service.EmployeeService;
import edu.step.employeemanager.service.exception.EmployeeNotFoundException;
import edu.step.employeemanager.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {


    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody EmployeeDTO employee) {
        try {
            return ResponseEntity.ok(this.employeeService.save(employee));
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getAll() {
        final List<EmployeeDTO> allEmployees = this.employeeService.findAll();
        return ResponseEntity.ok(allEmployees);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Integer id) {

        try {
            return ResponseEntity.ok(this.employeeService.findById(id));
        } catch (EmployeeNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody EmployeeDTO employeeToUpdate) {
        try {
            return ResponseEntity.ok(this.employeeService.update(employeeToUpdate));
        } catch (EmployeeNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            this.employeeService.delete(id);
            return ResponseEntity.ok("Employee deleted successfully");
        } catch (EmployeeNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    // CUSTOM METHODS

    @GetMapping("/filter")
    public ResponseEntity getAllWithSalaryAbove(@RequestParam("salaryAbove") Double salary) {
        final List<EmployeeDTO> allEmployees = this.employeeService.getEmployeesWithSalaryAbove(salary);
        return ResponseEntity.ok(allEmployees);
    }
    
}
