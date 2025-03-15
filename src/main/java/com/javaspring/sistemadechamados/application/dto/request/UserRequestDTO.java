package com.javaspring.sistemadechamados.application.dto.request;

import java.util.UUID;

import com.javaspring.sistemadechamados.application.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
    @NotNull(message = "Name must not be null")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    String name,

    @NotNull(message = "Email must not be null")
    @Email(message = "Email must be a valid email address")
    @Size(max = 255, message = "Email should not exceed 255 characters")
    String email,

    @NotNull(message = "Password must not be null")
    @Size(min = 8, message = "Password must be at least 8 characters long")
     String password,

    @NotNull(message = "Role must not be null")
    Role role,

    @NotNull(message = "Company ID must not be null")
    UUID companyId
) {}
