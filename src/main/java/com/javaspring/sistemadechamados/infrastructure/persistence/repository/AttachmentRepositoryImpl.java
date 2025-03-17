package com.javaspring.sistemadechamados.infrastructure.persistence.repository;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.javaspring.sistemadechamados.domain.model.Attachment;
import com.javaspring.sistemadechamados.domain.repositoryports.AttachmentRepository;
import com.javaspring.sistemadechamados.infrastructure.persistence.entity.AttachmentEntity;
import com.javaspring.sistemadechamados.infrastructure.persistence.repositoryjpa.AttchamentRepositoryJpa;
 
@Component
public class AttachmentRepositoryImpl implements AttachmentRepository {
    private final AttchamentRepositoryJpa attachmentRepositoryJpa;
    private final ModelMapper modelMapper;

    public AttachmentRepositoryImpl(AttchamentRepositoryJpa attachmentRepositoryJpa, ModelMapper modelMapper) {
        this.attachmentRepositoryJpa = attachmentRepositoryJpa;
        this.modelMapper = modelMapper;
    }

    @Override
    public Attachment uploadFile(Attachment attachment) {
        AttachmentEntity attachmentEntity = modelMapper.map(attachment, AttachmentEntity.class);

        AttachmentEntity savedAttachmentEntity = attachmentRepositoryJpa.save(attachmentEntity);
        return modelMapper.map(savedAttachmentEntity, Attachment.class);
    
    }

    @Override
    public Page <Attachment> getAttchmentsByTicketId(UUID ticketId,  Pageable pageable) {
        Page<AttachmentEntity> attachmentEntities = attachmentRepositoryJpa.findByTicket_Id(ticketId, pageable);
        return attachmentEntities.map(attachmentEntity -> modelMapper.map(attachmentEntity, Attachment.class));
       
    }

    @Override
    public void deleteAttachment(UUID id) {
       attachmentRepositoryJpa.deleteById(id);
    }

}
