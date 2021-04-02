package edu.step.employeemanager.service;

import edu.step.employeemanager.model.Company;
import edu.step.employeemanager.model.dto.CompanyDTO;
import edu.step.employeemanager.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyDTOService {
    @Autowired
    private CompanyRepository companyRepository;

    public void create(CompanyDTO dto) {
        final Company company = new Company();
        company.setName(dto.getName());
        this.companyRepository.save(company);
    }

    public List<CompanyDTO> getAll() {
        // get all companies
        final List<Company> all = this.companyRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        // convert to DTO
        List<CompanyDTO> dtos = all.stream().map(company -> CompanyDTO.from(company)
        ).collect(Collectors.toList());
        return dtos;
    }

    public CompanyDTO getById(Integer id) throws Exception {
        final Optional<Company> optionalCompany = this.companyRepository.findById(id);
        if(!optionalCompany.isPresent()){
            throw new Exception("Company with id=" + id +" does not exist.");
        }
        final Company company = optionalCompany.get();
       return CompanyDTO.from(company);
    }

    public void update(CompanyDTO dto) throws Exception{
        final Integer companyId = dto.getId();
        Optional<Company> optionalCompany =  this.companyRepository.findById(companyId);
        if(!optionalCompany.isPresent()){
            throw new Exception("Company with id=" + companyId +" does not exist.");
        }
        final Company company = optionalCompany.get();
        company.setName(dto.getName());
        this.companyRepository.save(company);
    }

    public void delete(Integer id) throws Exception {
        Company company = this.companyRepository
                .findById(id)
                .orElseThrow(() -> new Exception("Company does not exist"));
        this.companyRepository.delete(company);
    }
}
