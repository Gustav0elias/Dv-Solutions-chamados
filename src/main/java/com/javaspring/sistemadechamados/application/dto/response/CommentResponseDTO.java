package com.javaspring.sistemadechamados.application.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentResponseDTO(
    UUID id,
    String content,
    LocalDateTime createdAt,
    UUID createdByUserId,
    UUID ticketId
) {}