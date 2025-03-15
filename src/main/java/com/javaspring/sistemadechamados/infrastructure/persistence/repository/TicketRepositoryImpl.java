package com.javaspring.sistemadechamados.infrastructure.persistence.repository;

import java.time.LocalDateTime;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.javaspring.sistemadechamados.application.enums.TicketStatus;
import com.javaspring.sistemadechamados.domain.model.Ticket;
import com.javaspring.sistemadechamados.domain.repositoryports.TicketRepository;
import com.javaspring.sistemadechamados.domain.repositoryports.UserRepository;
import com.javaspring.sistemadechamados.infrastructure.persistence.entity.TicketEntity;
import com.javaspring.sistemadechamados.infrastructure.persistence.entity.UserEntity;
import com.javaspring.sistemadechamados.infrastructure.persistence.repositoryjpa.TicketRepositoryJpa;
import com.javaspring.sistemadechamados.web.exception.ResourceNotFoundException;

@Component
public class TicketRepositoryImpl implements TicketRepository {
    private final TicketRepositoryJpa ticketRepositoryJpa;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
 
    public TicketRepositoryImpl(TicketRepositoryJpa ticketRepositoryJpa, ModelMapper modelMapper, UserRepository userRepository) {
        this.ticketRepositoryJpa = ticketRepositoryJpa;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
     }

    @Override
    public Ticket createTicket(Ticket ticket) {
    

        TicketEntity ticketEntity = modelMapper.map(ticket, TicketEntity.class);
        ticketEntity.setStatus(TicketStatus.OPEN);
        ticketEntity = ticketRepositoryJpa.save(ticketEntity);
        return modelMapper.map(ticketEntity, Ticket.class);
        
    }

    @Override
    public Ticket getTicketById(UUID id) {
      return ticketRepositoryJpa.findById(id)
            .map(ticketEntity -> modelMapper.map(ticketEntity, Ticket.class))
            .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    @Override
    public Page<Ticket> getAllTicketsByUserId(Pageable pageable, UUID userId) {
       Page<TicketEntity> ticketEntities = ticketRepositoryJpa.findByCreatedByUser_Id(pageable, userId);
        return ticketEntities.map(ticketEntity -> modelMapper.map(ticketEntity, Ticket.class));
    }

    @Override
    public Ticket updateTicket(UUID id, Ticket ticket) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateTicket'");
    }

    @Override
    public Ticket attendTicket(UUID ticketId,  UUID responsibleUserId) {
      TicketEntity existingTicketEntity = ticketRepositoryJpa.findById(ticketId).orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));
      if (existingTicketEntity.getStatus() != TicketStatus.OPEN) {
        throw new IllegalStateException("Only OPEN tickets can be attended");
        }
        existingTicketEntity.setStatus(TicketStatus.IN_PROGRESS);
        existingTicketEntity.setResponsibleUser(modelMapper.map(
            userRepository.getUserById(responsibleUserId)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + responsibleUserId)),
            UserEntity.class
    ));
        existingTicketEntity.setUpdatedAt(LocalDateTime.now());
        TicketEntity ticketEntitySaved = ticketRepositoryJpa.save(existingTicketEntity);
        return modelMapper.map(ticketEntitySaved, Ticket.class);
    }

    @Override
    public Ticket closeTicket(UUID ticketId,  UUID responsibleUserId) {
        TicketEntity existingTicketEntity = ticketRepositoryJpa.findById(ticketId).orElseThrow(() -> new ResourceNotFoundException("Ticket not found"));
        if (existingTicketEntity.getStatus() != TicketStatus.IN_PROGRESS) {
          throw new IllegalStateException("Only IN PROGRESS tickets can be attended");
          }
          existingTicketEntity.setStatus(TicketStatus.CLOSED);
          existingTicketEntity.setUpdatedAt(LocalDateTime.now());
          TicketEntity ticketEntitySaved = ticketRepositoryJpa.save(existingTicketEntity);
          return modelMapper.map(ticketEntitySaved, Ticket.class);
    }

}
