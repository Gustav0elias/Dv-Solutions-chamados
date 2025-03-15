package com.javaspring.sistemadechamados.domain.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.javaspring.sistemadechamados.application.enums.TicketPriority;
import com.javaspring.sistemadechamados.application.enums.TicketStatus;

public class Ticket {

    private UUID id;
    private String title;
    private String description;
    private TicketStatus status;
    private TicketPriority priority;
    private String category;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private User createdByUser;
    private User responsibleUser;
    private Company company;
    private List <Comment> comments;
    private List <Attachment> attachments;


    public Ticket() {
    }


    public Ticket(UUID id, String title, String description, TicketStatus status, TicketPriority priority,
            String category, LocalDateTime createdAt, LocalDateTime updatedAt, User createdByUser, User responsibleUser,
            Company company, List<Comment> comments, List<Attachment> attachments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.createdByUser = createdByUser;
        this.responsibleUser = responsibleUser;
        this.company = company;
        this.comments = comments;
        this.attachments = attachments;
    }


    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public TicketStatus getStatus() {
        return status;
    }


    public void setStatus(TicketStatus status) {
        this.status = status;
    }


    public TicketPriority getPriority() {
        return priority;
    }


    public void setPriority(TicketPriority priority) {
        this.priority = priority;
    }


    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }


    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    public User getCreatedByUser() {
        return createdByUser;
    }


    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }


    public User getResponsibleUser() {
        return responsibleUser;
    }


    public void setResponsibleUser(User responsibleUser) {
        this.responsibleUser = responsibleUser;
    }


    public Company getCompany() {
        return company;
    }


    public void setCompany(Company company) {
        this.company = company;
    }


    public List<Comment> getComments() {
        return comments;
    }


    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


    public List<Attachment> getAttachments() {
        return attachments;
    }


    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    
}