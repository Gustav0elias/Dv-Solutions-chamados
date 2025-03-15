package com.javaspring.sistemadechamados.domain.serviceports;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javaspring.sistemadechamados.domain.model.Company;

public interface CompanyService {
public Company createCompany(Company company);
public Page<Company> getAllCompanies(Pageable pageable);
public Company getCompanyById(UUID id);
public Company updateCompany(UUID id, Company company);
public void deleteCompany(UUID id);
    
}  
