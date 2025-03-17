package com.javaspring.sistemadechamados.web.controller;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaspring.sistemadechamados.application.dto.request.CommentRequestDTO;
import com.javaspring.sistemadechamados.application.dto.response.CommentResponseDTO;
import com.javaspring.sistemadechamados.application.dto.response.PaginatedResponseDTO;
import com.javaspring.sistemadechamados.application.mapper.CommentMapper;
import com.javaspring.sistemadechamados.domain.model.Comment;
import com.javaspring.sistemadechamados.domain.serviceports.CommentService;

@RestController
@RequestMapping("/dvsolutions/comments")
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;
    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @PostMapping
    ResponseEntity<CommentResponseDTO> createComment(@RequestBody @Validated CommentRequestDTO commentRequestDTO) {
        Comment comment = commentMapper.toEntity(commentRequestDTO);
        Comment createdComment = commentService.createComment(comment);
        CommentResponseDTO commentResponseDTO = commentMapper.toResponseDTO(createdComment);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentResponseDTO);
    }

    @GetMapping("/{ticketId}/ticket")
    ResponseEntity<PaginatedResponseDTO<CommentResponseDTO>> getCommentsByTicketId (@PathVariable UUID ticketId, @PageableDefault(page = 0, size = 10) Pageable pageable ) {
       Page<CommentResponseDTO> comments = commentService.getCommentsByTicketId(ticketId, pageable).map(commentMapper::toResponseDTO);
        return ResponseEntity.ok(new PaginatedResponseDTO<>(comments));
    }
 
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteComment(@PathVariable UUID id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    ResponseEntity<CommentResponseDTO> updateComment(@PathVariable UUID id, @RequestBody @Validated CommentRequestDTO commentRequestDTO) {
        Comment comment = commentMapper.toEntity(commentRequestDTO);
        Comment updatedComment = commentService.updateComment(id, comment);
        CommentResponseDTO commentResponseDTO = commentMapper.toResponseDTO(updatedComment);
        return ResponseEntity.ok(commentResponseDTO);
    }
}
