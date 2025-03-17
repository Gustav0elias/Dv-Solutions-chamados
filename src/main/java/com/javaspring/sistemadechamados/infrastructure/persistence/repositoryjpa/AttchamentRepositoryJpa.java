package com.javaspring.sistemadechamados.infrastructure.persistence.repositoryjpa;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.javaspring.sistemadechamados.infrastructure.persistence.entity.AttachmentEntity;

public interface AttchamentRepositoryJpa extends JpaRepository<AttachmentEntity, UUID> {

    Page<AttachmentEntity> findByTicket_Id(UUID ticketId, Pageable pageable);

}
