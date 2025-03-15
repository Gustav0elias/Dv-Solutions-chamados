package com.javaspring.sistemadechamados.infrastructure.persistence.repositoryjpa;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaspring.sistemadechamados.infrastructure.persistence.entity.CompanyEntity;

public interface CompanyRepositoryJpa extends JpaRepository<CompanyEntity, UUID> {

    Optional<CompanyEntity> findByCnpj(String cnpj);

}
