package com.javaspring.sistemadechamados.web.controller;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaspring.sistemadechamados.application.dto.request.AttachmentRequestDTO;
import com.javaspring.sistemadechamados.application.dto.response.AttachmentResponseDTO;
import com.javaspring.sistemadechamados.application.dto.response.PaginatedResponseDTO;
import com.javaspring.sistemadechamados.application.mapper.AttachmentMapper;
import com.javaspring.sistemadechamados.domain.model.Attachment;
import com.javaspring.sistemadechamados.domain.serviceports.AttachmentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/dvsolutions/attachment")
public class AttchamentController {

    private final AttachmentService attachmentService;
    public AttchamentController(AttachmentService attachmentService, AttachmentMapper attachmentMapper) {
        this.attachmentService = attachmentService;
        this.attachmentMapper = attachmentMapper;
    }
    private final AttachmentMapper attachmentMapper;
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<AttachmentResponseDTO> uploadAttachment(   @RequestParam("file") @Valid MultipartFile file,
            @RequestParam("ticketId") UUID ticketId) {
         AttachmentRequestDTO attachmentRequestDTO = new AttachmentRequestDTO(file, ticketId);
        Attachment attachment = attachmentMapper.toModel(attachmentRequestDTO);
        Attachment savedAttachment = attachmentService.uploadFile(attachment);
        AttachmentResponseDTO responseDTO = attachmentMapper.toResponseDTO(savedAttachment);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/ticket/{ticketId}")
    ResponseEntity<PaginatedResponseDTO<AttachmentResponseDTO>> getAttachmentsByTicketId(@PathVariable UUID ticketId, @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<AttachmentResponseDTO> response = attachmentService.getAttchmentsByTicketId(ticketId, pageable).map(attachmentMapper::toResponseDTO);
        return ResponseEntity.ok(new PaginatedResponseDTO<>(response));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttachment(@PathVariable UUID id) {
        attachmentService.deleteAttachment(id);
        return ResponseEntity.noContent().build();
    }

}
