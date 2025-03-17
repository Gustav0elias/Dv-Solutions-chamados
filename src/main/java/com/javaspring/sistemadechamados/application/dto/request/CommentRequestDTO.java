package com.javaspring.sistemadechamados.application.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentRequestDTO(
    @NotBlank(message = "Content is required")
    String content,

    @NotNull(message = "Created by user ID is required")
    UUID createdByUserId,

    @NotNull(message = "Ticket ID is required")
    UUID ticketId
) {}
