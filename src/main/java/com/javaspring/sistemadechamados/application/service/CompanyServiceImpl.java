package com.javaspring.sistemadechamados.application.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

 import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javaspring.sistemadechamados.domain.model.Company;
import com.javaspring.sistemadechamados.domain.repositoryports.CompanyRepository;
import com.javaspring.sistemadechamados.domain.serviceports.CompanyService;
import com.javaspring.sistemadechamados.web.exception.ResourceNotFoundException;


public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company createCompany(Company company) {
      return companyRepository.createCompany(company);
    }

    @Override
    public Page<Company> getAllCompanies(Pageable pageable) {
      return companyRepository.getAllCompanies(pageable);
    }

    @Override
    public Company getCompanyById(UUID id) {
       return companyRepository.getCompanyById(id).orElseThrow(()-> new ResourceNotFoundException("Company not found"));
    }

    @Override
    public Company updateCompany(UUID id, Company company) {
     return companyRepository.updateCompany(id, company);
    }

    @Override
    public void deleteCompany(UUID id) {
        Company company = getCompanyById(id);
     companyRepository.deleteCompany(id);
    }

}
