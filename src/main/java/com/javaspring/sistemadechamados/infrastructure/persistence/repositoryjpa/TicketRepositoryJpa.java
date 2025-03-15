package com.javaspring.sistemadechamados.infrastructure.persistence.repositoryjpa;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.javaspring.sistemadechamados.infrastructure.persistence.entity.TicketEntity;
public interface TicketRepositoryJpa extends JpaRepository<TicketEntity, UUID> {

    Page<TicketEntity> findByCreatedByUser_Id(Pageable pageable, UUID userId);

}
