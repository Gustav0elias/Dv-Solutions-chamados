package com.javaspring.sistemadechamados.application.dto.response;

import java.util.List;

import org.springframework.data.domain.Page;

public record PaginatedResponseDTO<T> (
    List<T> content,
    int page,
    int size,
    long totalElements,
    int totalPages
)  {
    public PaginatedResponseDTO(Page<T> page) {
        this(
            page.getContent(),
            page.getPageable().getPageNumber(),
            page.getPageable().getPageSize(),
            page.getTotalElements(),
            page.getTotalPages()
        );
    }

}
