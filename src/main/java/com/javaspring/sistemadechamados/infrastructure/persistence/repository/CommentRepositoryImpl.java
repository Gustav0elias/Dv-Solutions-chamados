package com.javaspring.sistemadechamados.infrastructure.persistence.repository;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.javaspring.sistemadechamados.domain.model.Comment;
import com.javaspring.sistemadechamados.domain.repositoryports.CommentRepository;
import com.javaspring.sistemadechamados.infrastructure.persistence.entity.CommentEntity;
import com.javaspring.sistemadechamados.infrastructure.persistence.repositoryjpa.CommentRepositoryJpa;
import com.javaspring.sistemadechamados.web.exception.ResourceNotFoundException;

 @Component
public class CommentRepositoryImpl implements  CommentRepository {
    private final CommentRepositoryJpa commentRepositoryJpa;
    private final ModelMapper modelMapper;

    public CommentRepositoryImpl(CommentRepositoryJpa commentRepositoryJpa, ModelMapper modelMapper) {
        this.commentRepositoryJpa = commentRepositoryJpa;
        this.modelMapper = modelMapper;
    }

    @Override
    public Comment createComment(Comment comment) {
       CommentEntity commentEntity = modelMapper.map(comment, CommentEntity.class);
       commentEntity = commentRepositoryJpa.save(commentEntity);
       return modelMapper.map(commentEntity, Comment.class);
    }

    @Override
    public Comment updateComment(UUID id, Comment comment) {
      CommentEntity existingCommentEntity = commentRepositoryJpa.findById(id).orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
      existingCommentEntity.setId(id);
        CommentEntity savedComment = commentRepositoryJpa.save(modelMapper.map(comment, CommentEntity.class));
        return modelMapper.map(savedComment, Comment.class);
    }

    @Override
    public Comment getCommentByTicketId(UUID ticketId) {
       return commentRepositoryJpa.findByTicket_Id(ticketId).map(commentEntity -> modelMapper.map(commentEntity, Comment.class)).orElseThrow(()-> new ResourceNotFoundException("Comment not found"));
    }

    @Override
    public Page<Comment> getCommentsByTicketId(UUID ticketId, Pageable pageable) {
       return commentRepositoryJpa.findByTicket_Id(ticketId, pageable).map(commentEntity -> modelMapper.map(commentEntity, Comment.class));
    }

    @Override
    public void deleteComment(UUID id) {
       commentRepositoryJpa.deleteById(id);
    }

}
