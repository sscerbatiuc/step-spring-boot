package edu.step.employeemanager.api;

import edu.step.employeemanager.model.dto.CompanyDTO;
import edu.step.employeemanager.service.CompanyService;
import edu.step.employeemanager.service.exception.CompanyNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {

    private final CompanyService companyService;

    private static Logger log = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CompanyDTO company) {
        return ResponseEntity.ok(this.companyService.save(company));
    }

    @GetMapping
    public ResponseEntity getAll() {
        log.info("");
        final List<CompanyDTO> allCompanies = this.companyService.findAll();
        return ResponseEntity.ok(allCompanies);
    }

    @GetMapping("/active")
    public ResponseEntity getAllActive(){
        return ResponseEntity.ok(this.companyService.findCompaniesWithEmployees());
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable("id") Integer id) {

        try {
            return ResponseEntity.ok(this.companyService.findById(id));
        } catch (CompanyNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity update(@RequestBody CompanyDTO companyToUpdate) {
        try {
            return ResponseEntity.ok(this.companyService.update(companyToUpdate));
        } catch (CompanyNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            this.companyService.delete(id);
            return ResponseEntity.ok("Company deleted successfully");
        } catch (CompanyNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }

    // CUSTOM METHODS

}
