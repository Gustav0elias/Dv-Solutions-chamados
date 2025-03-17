package com.javaspring.sistemadechamados.application.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javaspring.sistemadechamados.application.enums.TicketStatus;
import com.javaspring.sistemadechamados.domain.model.Company;
import com.javaspring.sistemadechamados.domain.model.Ticket;
import com.javaspring.sistemadechamados.domain.model.User;
import com.javaspring.sistemadechamados.domain.repositoryports.CompanyRepository;
import com.javaspring.sistemadechamados.domain.repositoryports.TicketRepository;
import com.javaspring.sistemadechamados.domain.serviceports.CompanyService;
import com.javaspring.sistemadechamados.domain.serviceports.TicketService;
import com.javaspring.sistemadechamados.domain.serviceports.UserService;


 
public class TicketServiceImpl implements TicketService {

    private static final Logger logger = LoggerFactory.getLogger(TicketServiceImpl.class);
    private final TicketRepository ticketRepository;
    private final CompanyRepository companyRepository;
    private final UserService userService;

    public TicketServiceImpl(TicketRepository ticketRepository, CompanyRepository companyRepository, UserService userService) {
        this.ticketRepository = ticketRepository;
        this.companyRepository = companyRepository;
        this.userService = userService;
    }

    @Override
    public Ticket createTicket(Ticket ticket) {
        logger.info("Creating ticket with title: {}", ticket.getTitle());

        validateTicket(ticket); // Validações de negócio
        Ticket createdTicket = ticketRepository.createTicket(ticket);

        logger.info("Ticket created successfully with id: {}", createdTicket.getId());
        return createdTicket;
    }

    @Override
    public Ticket getTicketById(UUID id) {
        logger.info("Fetching ticket with id: {}", id);

        return ticketRepository.getTicketById(id);
                
    }

    @Override
    public Page<Ticket> getAllTicketsByUserId(Pageable pageable, UUID userId) {
        logger.info("Fetching tickets for user with id: {}", userId);

        return ticketRepository.getAllTicketsByUserId(pageable, userId);
    }

    @Override
    public Ticket updateTicket(UUID id, Ticket ticket) {
        logger.info("Updating ticket with id: {}", id);

        validateTicket(ticket); // Validações de negócio
        Ticket existingTicket = getTicketById(id); // Verifica se o ticket existe

        Ticket updatedTicket = ticketRepository.updateTicket(id, ticket);
        logger.info("Ticket updated successfully with id: {}", updatedTicket.getId());

        return updatedTicket;
    }

    @Override
    public Ticket attendTicket(UUID ticketId, UUID responsibleUserId) {
        logger.info("Attending ticket with id: {}", ticketId);

        userService.userTechnicianExists(responsibleUserId);
        Ticket existingTicket = getTicketById(ticketId);  
        validateTicketStatus(existingTicket, TicketStatus.OPEN);  

        Ticket attendedTicket = ticketRepository.attendTicket(ticketId, responsibleUserId);
        logger.info("Ticket attended successfully with id: {}", attendedTicket.getId());

        return attendedTicket;
    }

    @Override
    public Ticket closeTicket(UUID ticketId, UUID responsibleUserId) {
        logger.info("Closing ticket with id: {}", ticketId);
        userService.userTechnicianExists(responsibleUserId);
        Ticket existingTicket = getTicketById(ticketId);  
        validateTicketStatus(existingTicket, TicketStatus.IN_PROGRESS); 

        Ticket closedTicket = ticketRepository.closeTicket(ticketId, responsibleUserId);
        logger.info("Ticket closed successfully with id: {}", closedTicket.getId());

        return closedTicket;
    }

     private void validateTicket(Ticket ticket) {
        if (ticket.getTitle() == null || ticket.getTitle().isBlank()) {
            throw new IllegalArgumentException("Ticket title is required");
        }
        if (ticket.getDescription() == null || ticket.getDescription().isBlank()) {
            throw new IllegalArgumentException("Ticket description is required");
        }
        if (ticket.getCreatedByUser() == null) {
            throw new IllegalArgumentException("Created by user ID is required");
        }
        if (ticket.getCompany() == null) {
            throw new IllegalArgumentException("Company ID is required");
        }
    }

    private void validateTicketStatus(Ticket ticket, TicketStatus expectedStatus) {
        if (ticket.getStatus() != expectedStatus) {
            throw new IllegalStateException("Ticket must be " + expectedStatus + " to perform this action");
        }
    }
}