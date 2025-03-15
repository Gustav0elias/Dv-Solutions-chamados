package com.javaspring.sistemadechamados.application.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.javaspring.sistemadechamados.application.enums.TicketPriority;
import com.javaspring.sistemadechamados.application.enums.TicketStatus;

public record TicketResponseDTO(
    UUID id,
    String title,
    String description,
    TicketStatus status,
    TicketPriority priority,
    String category,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    UUID createdByUserId,  
    UUID responsibleUserId,  
    UUID companyId
   // List<CommentResponseDTO> comments, // Lista de comentários
    //List<AttachmentResponseDTO> attachments // Lista de anexos
) {}