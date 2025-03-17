package com.javaspring.sistemadechamados.domain.model;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class Attachment {

    private UUID id;
    private String fileName;
    private String fileType;
    private String filePath;
    private Ticket ticket;
     private MultipartFile file;

    public Attachment() {
    }
    public Attachment(UUID id, String fileName, String fileType, String filePath, Ticket ticket, MultipartFile file) {
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
    public Ticket getTicket() {
        return ticket;
    }
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    
}
