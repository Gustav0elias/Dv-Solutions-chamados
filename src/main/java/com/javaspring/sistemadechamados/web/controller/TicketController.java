package com.javaspring.sistemadechamados.web.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaspring.sistemadechamados.application.dto.request.TicketRequestDTO;
import com.javaspring.sistemadechamados.application.dto.response.PaginatedResponseDTO;
import com.javaspring.sistemadechamados.application.dto.response.TicketResponseDTO;
import com.javaspring.sistemadechamados.application.mapper.TicketMapper;
import com.javaspring.sistemadechamados.config.BeansConfiguration;
import com.javaspring.sistemadechamados.domain.model.Ticket;
import com.javaspring.sistemadechamados.domain.serviceports.TicketService;
@RestController
@RequestMapping("/dvsolutions/tickets")
public class TicketController {

    private static final Logger logger = LoggerFactory.getLogger(TicketController.class);
    private final TicketService ticketService;
    private final TicketMapper ticketMapper;

    public TicketController(TicketService ticketService, TicketMapper ticketMapper) {
        this.ticketService = ticketService;
        this.ticketMapper = ticketMapper;
    }

    @PostMapping
    public ResponseEntity<TicketResponseDTO> createTicket(@RequestBody @Validated TicketRequestDTO ticketRequest) {
        logger.info("Creating ticket with title: {}", ticketRequest.title());
        Ticket ticket = ticketMapper.toEntity(ticketRequest);
        ticket = ticketService.createTicket(ticket);
        TicketResponseDTO ticketResponse = ticketMapper.toResponseDTO(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> getTicketById(@PathVariable UUID id) {
        logger.info("Fetching ticket with id: {}", id);
        Ticket ticket = ticketService.getTicketById(id);
        TicketResponseDTO ticketResponse = ticketMapper.toResponseDTO(ticket);
        return ResponseEntity.ok(ticketResponse);
    }

    @GetMapping("/{userId}/tickets")
    public ResponseEntity<PaginatedResponseDTO<TicketResponseDTO>> getTicketsByUserId(
            @PathVariable UUID userId,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        logger.info("Fetching tickets for user with id: {}", userId);
        Page<TicketResponseDTO> tickets = ticketService.getAllTicketsByUserId(pageable, userId)
                .map(ticketMapper::toResponseDTO);
        return ResponseEntity.ok(new PaginatedResponseDTO<>(tickets));
    }

    @PutMapping("/{ticketId}/attend/{responsibleUserId}")
    public ResponseEntity<TicketResponseDTO> attendTicket(
            @PathVariable UUID ticketId,
            @PathVariable UUID responsibleUserId) {
        logger.info("Attending ticket with id: {}", ticketId);
        Ticket attendedTicket = ticketService.attendTicket(ticketId, responsibleUserId);
        TicketResponseDTO ticketResponse = ticketMapper.toResponseDTO(attendedTicket);
        return ResponseEntity.ok(ticketResponse);
    }

    @PutMapping("/{ticketId}/close/{responsibleUserId}")
    public ResponseEntity<TicketResponseDTO> closeTicket(
            @PathVariable UUID ticketId,
            @PathVariable UUID responsibleUserId) {
        logger.info("Closing ticket with id: {}", ticketId);
        Ticket closedTicket = ticketService.closeTicket(ticketId, responsibleUserId);
        TicketResponseDTO ticketResponse = ticketMapper.toResponseDTO(closedTicket);
        return ResponseEntity.ok(ticketResponse);
    }
}