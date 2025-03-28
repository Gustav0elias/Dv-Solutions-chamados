package com.javaspring.sistemadechamados.domain.serviceports;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javaspring.sistemadechamados.domain.model.Attachment;

public interface AttachmentService{
public Attachment uploadFile (Attachment attachment);
public Page <Attachment> getAttchmentsByTicketId(UUID ticketId, Pageable pageable);
public void deleteAttachment(UUID id);
public String generateUniqueAttachamentName (String fileName);
}
