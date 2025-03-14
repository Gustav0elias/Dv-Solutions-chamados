package com.javaspring.sistemadechamados.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Comment {

    private UUID id;
    private String content;
    private LocalDateTime createdAt;
    private User createdBy;
    private Ticket ticket;
}
