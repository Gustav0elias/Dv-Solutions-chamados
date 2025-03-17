package com.javaspring.sistemadechamados.infrastructure.persistence.repositoryjpa;

import java.lang.StackWalker.Option;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.javaspring.sistemadechamados.domain.model.Comment;
import com.javaspring.sistemadechamados.infrastructure.persistence.entity.CommentEntity;

public interface CommentRepositoryJpa extends JpaRepository<CommentEntity, UUID> {

    Page<CommentEntity> findByTicket_Id(UUID ticketId,Pageable pageable );
    Optional<CommentEntity> findByTicket_Id(UUID ticketId);

}
