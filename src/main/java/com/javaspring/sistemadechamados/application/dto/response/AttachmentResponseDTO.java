package com.javaspring.sistemadechamados.application.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record AttachmentResponseDTO(
    UUID id,
    String fileName,
    String fileType,
    String filePath,
    
    UUID ticketId,
    LocalDateTime createdAt
) {}
