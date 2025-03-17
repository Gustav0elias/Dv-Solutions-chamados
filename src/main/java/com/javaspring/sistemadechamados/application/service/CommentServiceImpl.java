package com.javaspring.sistemadechamados.application.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javaspring.sistemadechamados.domain.model.Comment;
import com.javaspring.sistemadechamados.domain.repositoryports.CommentRepository;
import com.javaspring.sistemadechamados.domain.serviceports.CommentService;

public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment createComment(Comment comment) {
       Comment createdComment = commentRepository.createComment(comment);
       return createdComment;
    }

    @Override
    public Comment updateComment(UUID id, Comment comment) {
        if(getCommentByTicketId(id) == null) {
            throw new RuntimeException("Comment not found");
        }
        Comment updatedComment = commentRepository.updateComment(id, comment);
        return updatedComment;
    }

    @Override
    public Comment getCommentByTicketId(UUID ticketId) {
       return commentRepository.getCommentByTicketId(ticketId);
    }

    @Override
    public Page<Comment> getCommentsByTicketId(UUID ticketId, Pageable pageable) {
       return commentRepository.getCommentsByTicketId(ticketId, pageable);
    }

    @Override
    public void deleteComment(UUID id) {
       commentRepository.deleteComment(id);
    }

}
