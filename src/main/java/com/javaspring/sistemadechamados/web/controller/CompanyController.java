package com.javaspring.sistemadechamados.web.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaspring.sistemadechamados.application.dto.request.CompanyRequestDTO;
import com.javaspring.sistemadechamados.application.dto.response.CompanyResponseDTO;
import com.javaspring.sistemadechamados.application.dto.response.PaginatedResponseDTO;
import com.javaspring.sistemadechamados.application.mapper.CompanyMapper;
import com.javaspring.sistemadechamados.domain.model.Company;
import com.javaspring.sistemadechamados.domain.serviceports.CompanyService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("dvsolutions/companies")
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    public CompanyController(CompanyService companyService, CompanyMapper companyMapper) {
        this.companyService = companyService;
        this.companyMapper = companyMapper;
    }


    @PostMapping
    public ResponseEntity <CompanyResponseDTO> createCompany(@RequestBody CompanyRequestDTO companyRequestDTO) {
        Company company = companyMapper.toEntity(companyRequestDTO);
        company = companyService.createCompany(company);
        CompanyResponseDTO companyResponseDTO = companyMapper.toResponseDTO(company);
        return ResponseEntity.status(HttpStatus.CREATED).body(companyResponseDTO);
    }

    @Operation(summary = "Get all companies", description = "Retrieve all companies with pagination")
    @GetMapping
    public ResponseEntity<PaginatedResponseDTO<CompanyResponseDTO>> getAllCompanies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
       Page<CompanyResponseDTO> companies = companyService.getAllCompanies(PageRequest.of(page, size)).map(companyMapper::toResponseDTO);
       return ResponseEntity.ok(new PaginatedResponseDTO<>(companies));
    }

    @Operation(summary = "Update company", description = "Update an existing company's information by their ID")
    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDTO> getCompanyById(@PathVariable UUID id) {
    CompanyResponseDTO companyResponseDTO = companyMapper.toResponseDTO(companyService.getCompanyById(id));
    return ResponseEntity.ok(companyResponseDTO);
    }

    @Operation(summary = "Update company", description = "Update an existing company's information by their ID")
    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponseDTO> updateCompany(@PathVariable UUID id, @RequestBody CompanyRequestDTO companyRequestDTO) {
        Company company = companyMapper.toEntity(companyRequestDTO);
        companyService.updateCompany(id, company);
        return ResponseEntity.ok(companyMapper.toResponseDTO(company));
    }
    
    @Operation(summary = "Delete company", description = "Delete a company by their unique ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable UUID id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

}
