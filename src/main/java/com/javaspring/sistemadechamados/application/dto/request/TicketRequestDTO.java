package com.javaspring.sistemadechamados.application.dto.request;

import java.util.UUID;

import com.javaspring.sistemadechamados.application.enums.TicketPriority;
import com.javaspring.sistemadechamados.application.enums.TicketStatus;

import jakarta.validation.constraints.NotNull;

public record TicketRequestDTO(
    @NotNull(message="Title is required")
    String title,
    @NotNull(message="Description is required")
    String description,
    @NotNull(message="Status is required")
    String category,

    UUID createdByUserId, 
    @NotNull(message="Responsible User is required")
    UUID companyId  
) {}
