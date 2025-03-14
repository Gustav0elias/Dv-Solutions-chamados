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

    public Company() {
    }
    public Company(UUID id, String name, String cnpj, String address, String phone, List <Ticket> tickets, List <User> technicians) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.address = address;
        this.phone = phone;
        this.tickets = tickets;
        Technicians = technicians;
    }
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public List<Ticket> getTickets() {
        return tickets;
    }
    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
    public List<User> getTechnicians() {
        return Technicians;
    }
    public void setTechnicians(List<User> technicians) {
        Technicians = technicians;
    }
    
}
