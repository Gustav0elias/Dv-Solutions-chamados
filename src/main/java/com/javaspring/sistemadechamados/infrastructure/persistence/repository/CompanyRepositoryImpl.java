package com.javaspring.sistemadechamados.infrastructure.persistence.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.javaspring.sistemadechamados.domain.model.Company;
import com.javaspring.sistemadechamados.domain.repositoryports.CompanyRepository;
import com.javaspring.sistemadechamados.domain.serviceports.CompanyService;
import com.javaspring.sistemadechamados.infrastructure.persistence.entity.CompanyEntity;
import com.javaspring.sistemadechamados.infrastructure.persistence.repositoryjpa.CompanyRepositoryJpa;
import com.javaspring.sistemadechamados.web.exception.ResourceNotFoundException;

@Component
public class CompanyRepositoryImpl implements  CompanyRepository {

 
    private final CompanyRepositoryJpa companyRepositoryJpa;
    private final ModelMapper modelMapper;
    public CompanyRepositoryImpl(CompanyRepositoryJpa companyRepositoryJpa, ModelMapper modelMapper) {
        this.companyRepositoryJpa = companyRepositoryJpa;
        this.modelMapper = modelMapper;
     
    }

    @Override
    public Company createCompany(Company company) {
       companyRepositoryJpa.findByCnpj(company.getCnpj()).ifPresent(existingCompany -> {
        throw new ResourceNotFoundException("Company already exists");
       });
      CompanyEntity companyEntity = modelMapper.map(company, CompanyEntity.class);
      companyEntity = companyRepositoryJpa.save(companyEntity);
      return modelMapper.map(companyEntity, Company.class);
    }

    @Override
    public Page<Company> getAllCompanies(Pageable pageable) {
        Page<CompanyEntity> companyEntities = companyRepositoryJpa.findAll(pageable);
        return companyEntities.map(companyEntity -> modelMapper.map(companyEntity, Company.class));
    }

    @Override
    public Optional <Company> getCompanyById(UUID id) {
       return companyRepositoryJpa.findById(id)
        .map(companyEntity -> modelMapper.map(companyEntity, Company.class));
    }

    @Override
    public Company updateCompany(UUID id, Company company) {
        CompanyEntity existingCompanyEntity = companyRepositoryJpa.findById(id) 
          .orElseThrow(() -> new ResourceNotFoundException("Company not found whit ID: " + id));
         companyRepositoryJpa.save(existingCompanyEntity);
         return modelMapper.map(existingCompanyEntity, Company.class);
        
    }

    @Override
    public void deleteCompany(UUID id) {
        CompanyEntity existingCompanyEntity = companyRepositoryJpa.findById(id) 
        .orElseThrow(() -> new ResourceNotFoundException("Company not found whit ID: " + id));
        companyRepositoryJpa.delete(existingCompanyEntity);
    }

    @Override
    public Company findByCnpj(String cnpj) {
       return companyRepositoryJpa.findByCnpj(cnpj).map(companyEntity -> modelMapper.map(companyEntity, Company.class))
       .orElseThrow(()-> new ResourceNotFoundException("Company with CNPJ " + cnpj + " not found."));   
    }

}
