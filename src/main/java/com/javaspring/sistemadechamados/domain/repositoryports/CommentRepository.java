package com.javaspring.sistemadechamados.domain.repositoryports;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javaspring.sistemadechamados.domain.model.Comment;

public interface CommentRepository {

    public Comment createComment(Comment comment);
    public Comment updateComment(UUID id, Comment comment);
    public Comment getCommentByTicketId(UUID ticketId);
    public Page <Comment> getCommentsByTicketId(UUID ticketId, Pageable pageable);
    public void deleteComment(UUID id);
}
