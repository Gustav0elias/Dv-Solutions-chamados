package com.javaspring.sistemadechamados.infrastructure.persistence.entity;

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
@Table(name = "attachment_tb")
public class AttachmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String fileName;
    @Column(nullable = false)
    private String fileType;
    @Column(nullable = false)
    private String filePath;
    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private TicketEntity ticket;
    public AttachmentEntity(){}
    public AttachmentEntity(UUID id, String fileName, String fileType, String filePath, TicketEntity ticket) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.filePath = filePath;
        this.ticket = ticket;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public TicketEntity getTicket() {
        return ticket;
    }
    public void setTicket(TicketEntity ticket) {
        this.ticket = ticket;
    }
    
}
