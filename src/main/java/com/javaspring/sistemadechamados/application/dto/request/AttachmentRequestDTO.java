package com.javaspring.sistemadechamados.application.dto.request;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public record AttachmentRequestDTO(
    MultipartFile file,
    UUID ticketId
) {}