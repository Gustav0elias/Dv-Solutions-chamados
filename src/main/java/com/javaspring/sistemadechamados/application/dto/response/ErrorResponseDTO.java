package com.javaspring.sistemadechamados.application.dto.response;

import java.time.LocalDateTime;

public record ErrorResponseDTO (
    int statusCode,
    String message,
    LocalDateTime timestamp
) {

}
