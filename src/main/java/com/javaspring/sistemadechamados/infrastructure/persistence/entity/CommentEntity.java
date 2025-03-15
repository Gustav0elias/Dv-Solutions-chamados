package com.javaspring.sistemadechamados.infrastructure.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.javaspring.sistemadechamados.domain.model.Ticket;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comment_tb")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String content;
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity createdByUser;
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private TicketEntity ticket;

    public CommentEntity(){}

    public CommentEntity(UUID id, String content, LocalDateTime createdAt, UserEntity createdByUser,
            TicketEntity ticket) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.createdByUser = createdByUser;
        this.ticket = ticket;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserEntity getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(UserEntity createdByUser) {
        this.createdByUser = createdByUser;
    }

    public TicketEntity getTicket() {
        return ticket;
    }

    public void setTicket(TicketEntity ticket) {
        this.ticket = ticket;
    }
    

}
