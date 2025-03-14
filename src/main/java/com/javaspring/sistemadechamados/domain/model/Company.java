package com.javaspring.sistemadechamados.domain.model;

import java.util.List;
import java.util.UUID;

public class Company {
    private UUID id;
    private String name;
    private String cnpj;
    private String address;
    private String phone;
    private List <Ticket> tickets;
    private List <User> Technicians;

}
