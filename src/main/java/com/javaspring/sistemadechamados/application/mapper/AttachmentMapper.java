package com.javaspring.sistemadechamados.application.mapper;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.javaspring.sistemadechamados.application.dto.request.AttachmentRequestDTO;
import com.javaspring.sistemadechamados.application.dto.response.AttachmentResponseDTO;
import com.javaspring.sistemadechamados.domain.model.Attachment;
import com.javaspring.sistemadechamados.domain.model.Ticket;

@Component
public class AttachmentMapper {

    public Attachment toModel(AttachmentRequestDTO attachmentRequestDTO) {
        Attachment attachment = new Attachment();
        attachment.setFileName(attachmentRequestDTO.file().getOriginalFilename());
        attachment.setFileType(attachmentRequestDTO.file().getContentType());
         Ticket ticket = new Ticket();
        ticket.setId(attachmentRequestDTO.ticketId());
        attachment.setTicket(ticket);

        return attachment;
    }

    public AttachmentResponseDTO toResponseDTO(Attachment attachment) {
        return new AttachmentResponseDTO(
            attachment.getId(),
            attachment.getFileName(),
            attachment.getFileType(),
            attachment.getFilePath(),  
         
            attachment.getTicket().getId(),
            LocalDateTime.now()
        );
    }
}
