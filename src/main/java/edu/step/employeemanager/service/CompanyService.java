package edu.step.employeemanager.service;

import edu.step.employeemanager.model.Company;
import edu.step.employeemanager.model.dto.CompanyDTO;
import edu.step.employeemanager.repository.CompanyRepository;
import edu.step.employeemanager.service.exception.CompanyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public CompanyDTO update(CompanyDTO companyDTO) throws CompanyNotFoundException {
        final Company company = this.companyRepository.findById(companyDTO.getId()).orElseThrow(() -> new CompanyNotFoundException(companyDTO.getId()));
        company.setName(companyDTO.getName());
        final Company savedCompany = this.companyRepository.save(company);
        return CompanyDTO.from(savedCompany);
    }

    public List<CompanyDTO> findCompaniesWithEmployees() {
        final List<CompanyDTO> activeCompanies = this.companyRepository.findDistinctByEmployeesNotEmpty().stream().map(company -> CompanyDTO.from(company)).collect(Collectors.toList());
        return activeCompanies;
    }

    public CompanyDTO findById(Integer id) throws CompanyNotFoundException {
        final Company company = companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException(id));
        return CompanyDTO.from(company);
    }

    public List<CompanyDTO> findAll() {
        return companyRepository.findAll().stream().map(company -> CompanyDTO.from(company)).collect(Collectors.toList());
    }

    public CompanyDTO save(CompanyDTO companyDTO) {
        final Company newCompany = new Company();
        newCompany.setId(companyDTO.getId());
        newCompany.setName(companyDTO.getName());
        // if employees exist find them first
        return CompanyDTO.from(companyRepository.save(newCompany));
    }

    public void delete(Integer id) throws CompanyNotFoundException {
        final Company companyToDelete = this.companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException(id));
        this.companyRepository.delete(companyToDelete);
    }
}
