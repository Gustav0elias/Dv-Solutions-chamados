package com.javaspring.sistemadechamados.application.service;

import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javaspring.sistemadechamados.domain.model.Attachment;
import com.javaspring.sistemadechamados.domain.repositoryports.AttachmentRepository;
import com.javaspring.sistemadechamados.domain.serviceports.AttachmentService;

public class AttachmentServiceImpl implements AttachmentService {

    private final AttachmentRepository attachmentRepository;
    @Value("${file.upload-dir}")
    private String uploadDir;
    public AttachmentServiceImpl(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public Attachment uploadFile(Attachment attachment) {

        Path uploadPath = Paths.get(uploadDir);
        if(!Files.exists( uploadPath)){
            try{
                Files.createDirectories(uploadPath);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        String newFileName = generateUniqueAttachamentName(attachment.getFileName());
        attachment.setFileName(newFileName);

        Path filePath = uploadPath.resolve(newFileName);
        attachment.setFilePath(filePath.toString());
        try{
            Files.write(uploadPath.resolve(newFileName), attachment.getFilePath().getBytes());
        }catch(Exception e){
            e.printStackTrace();
        }
      

     return attachmentRepository.uploadFile(attachment);
    }

    @Override
    public Page <Attachment> getAttchmentsByTicketId(UUID ticketId, Pageable pageable) {
       return attachmentRepository.getAttchmentsByTicketId(ticketId, pageable);
    }

    @Override
    public void deleteAttachment(UUID id) {
        attachmentRepository.deleteAttachment(id);
    }

    @Override
    public String generateUniqueAttachamentName(String fileName) {
        String uniqueFileName = UUID.randomUUID().toString() + "_" + fileName;
        return uniqueFileName;
    }

}
