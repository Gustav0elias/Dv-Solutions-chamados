package com.javaspring.sistemadechamados.application.dto.response;

import java.util.List;
import java.util.UUID;

public record CompanyResponseDTO(
    UUID id,
    String name,
    String cnpj,
    String address,
    String phone
   // List<TicketResponseDTO> tickets, 
   // List<UserResponseDTO> technicians  
) {}