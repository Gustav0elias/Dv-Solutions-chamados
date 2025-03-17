package com.javaspring.sistemadechamados.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Comment {

    private UUID id;
    private String content;
    private LocalDateTime createdAt;
    private User createdBy;
    private Ticket ticket;

    public Comment() {
    }
    public Comment(UUID id, String content, User createdBy, Ticket ticket) {
        this.id = id;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.createdBy = createdBy;
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
    public User getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
    public Ticket getTicket() {
        return ticket;
    }
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    
}
