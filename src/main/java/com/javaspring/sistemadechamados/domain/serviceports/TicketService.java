package com.javaspring.sistemadechamados.domain.serviceports;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javaspring.sistemadechamados.domain.model.Ticket;

public interface TicketService {
    public Ticket createTicket(Ticket ticket);
    public Ticket getTicketById(UUID id);
    public Page<Ticket> getAllTicketsByUserId(Pageable pageable, UUID  userId);
    public Ticket updateTicket(UUID id, Ticket ticket);
    public Ticket attendTicket (UUID ticketId, UUID responsibleUserId);
    public Ticket closeTicket (UUID ticketId,  UUID responsibleUserId);
}

