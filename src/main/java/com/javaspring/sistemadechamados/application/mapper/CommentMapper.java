package com.javaspring.sistemadechamados.application.mapper;

import org.springframework.stereotype.Component;

import com.javaspring.sistemadechamados.application.dto.request.CommentRequestDTO;
import com.javaspring.sistemadechamados.application.dto.response.CommentResponseDTO;
import com.javaspring.sistemadechamados.domain.model.Comment;
import com.javaspring.sistemadechamados.domain.model.Ticket;
import com.javaspring.sistemadechamados.domain.model.User;

@Component
public class CommentMapper {

    public Comment toEntity(CommentRequestDTO commentRequestDTO) {
        Comment comment = new Comment();
        comment.setContent(commentRequestDTO.content());

         User createdBy = new User();
        createdBy.setId(commentRequestDTO.createdByUserId());
        comment.setCreatedBy(createdBy);

         Ticket ticket = new Ticket();
        ticket.setId(commentRequestDTO.ticketId());
        comment.setTicket(ticket);

        return comment;
    }

    public CommentResponseDTO toResponseDTO(Comment comment) {
        return new CommentResponseDTO(
            comment.getId(),
            comment.getContent(),
            comment.getCreatedAt(),
            comment.getCreatedBy().getId(),
            comment.getTicket().getId()
        );
    }
}