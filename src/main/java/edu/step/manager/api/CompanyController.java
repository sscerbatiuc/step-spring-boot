package edu.step.manager.api;

import edu.step.manager.model.Company;
import edu.step.manager.model.dto.CompanyDTO;
import edu.step.manager.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/company") // localhost:8080/rest/company
public class CompanyController {


    @Autowired
    private CompanyRepository companyRepository;

    // CREATE
    @PostMapping
    public ResponseEntity create(@RequestBody CompanyDTO dto) {
        final Company company = new Company();
        company.setName(dto.getName());
        this.companyRepository.save(company);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    // GET

    @GetMapping
    public ResponseEntity getAll() {
        // get all companies
        final List<Company> all = this.companyRepository.findAll();
        // convert to DTO
        List<CompanyDTO> dtos = all.stream().map(company -> {
            final CompanyDTO companyDTO = new CompanyDTO();
            companyDTO.setName(company.getName());
            companyDTO.setId(company.getId());
            if(company.getEmployees() != null) {
                final Set<Integer> ids = company.getEmployees().stream().map(employee -> employee.getId()).collect(Collectors.toSet());
                companyDTO.setEmployees(ids);
            }
            return companyDTO;
        }).collect(Collectors.toList());
        // return
        return ResponseEntity.ok(dtos);

    }
    // UPDATE
    // DELETE


}
