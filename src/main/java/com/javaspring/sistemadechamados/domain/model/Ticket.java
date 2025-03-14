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
    private User createdBy;
    private User assignedTo;
    private Company company;
    private List <Comment> comments;
    private List <Attachment> attachments;
}
