package com.javaspring.sistemadechamados.domain.repositoryports;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javaspring.sistemadechamados.domain.model.Attachment;

public interface AttachmentRepository {

public Attachment uploadFile (Attachment attachment);
public Page <Attachment> getAttchmentsByTicketId(  UUID ticketId, Pageable pageable);
public void deleteAttachment(UUID id);
}
