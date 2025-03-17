package com.javaspring.sistemadechamados.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.cglib.core.Local;

public class TicketChat {
    private UUID id;
    private String message;
    private UUID ticketId;
    private UUID userId;
    private LocalDateTime createdAt;


}
