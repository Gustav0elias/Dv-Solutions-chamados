package com.javaspring.sistemadechamados.application.dto.request;

import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CompanyRequestDTO(
    @NotNull(message = "Company name must not be null")
    @Size(min = 2, max = 100, message = "Company name must be between 2 and 100 characters")
    String name,

    @NotNull(message = "CNPJ must not be null")
    @Pattern(regexp = "^[0-9]{2}\\.([0-9]{3})\\.([0-9]{3})/([0-9]{4})-([0-9]{2})$", message = "CNPJ must be in the format XX.XXX.XXX/XXXX-XX")
    String cnpj,

    @NotNull(message = "Address must not be null")
    @Size(min = 5, max = 200, message = "Address must be between 5 and 200 characters")
    String address,

    @NotNull(message = "Phone must not be null")
    @Pattern(regexp = "^\\(\\d{2}\\) \\d{5}-\\d{4}$", message = "Phone must be in the format (XX) XXXXX-XXXX")
    String phone

    // @NotNull(message = "Technician IDs must not be null")
    // @Size(min = 1, message = "At least one technician ID must be provided")
    // List<UUID> technicianIds
) {}
