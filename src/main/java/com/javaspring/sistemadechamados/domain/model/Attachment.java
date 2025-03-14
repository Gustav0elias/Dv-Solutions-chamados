package com.javaspring.sistemadechamados.domain.model;

import java.util.UUID;

public class Attachment {

    private UUID id;
    private String fileName;
    private String fileType;
    private String filePath;
    private Ticket ticket;
}
