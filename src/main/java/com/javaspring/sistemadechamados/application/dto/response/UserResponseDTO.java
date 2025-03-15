package com.javaspring.sistemadechamados.application.dto.response;
import java.util.UUID;

import com.javaspring.sistemadechamados.application.enums.Role;
import com.javaspring.sistemadechamados.domain.model.Company;
public record UserResponseDTO(
    UUID id,
    String name,
    String email,
    Role role,
    Company company // DTO da empresa associada (pode ser null)
    //List<CompanyResponseDTO> companies, // Pode ser null ou uma lista vazia
    //List<TicketResponseDTO> ticketsCreated, // Pode ser null ou uma lista vazia
    //List<TicketResponseDTO> ticketsAssigned // Pode ser null ou uma lista vazia
) {}
