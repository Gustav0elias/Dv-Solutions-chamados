package com.javaspring.sistemadechamados.infrastructure.persistence.entity;

import java.util.List;
import java.util.UUID;

import com.javaspring.sistemadechamados.domain.model.Company;
import com.javaspring.sistemadechamados.domain.model.Ticket;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "company_tb")
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String cnpj;
    private String address;
    @Column(nullable = false)
    private String phone;
    @OneToMany(mappedBy = "company")
    private List <TicketEntity> tickets;
    @ManyToMany
    @JoinTable(
        name = "company_technician",
        joinColumns = @JoinColumn(name = "company_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> technicians; 

    public CompanyEntity(){}


    public CompanyEntity(UUID id, String name, String cnpj, String address, String phone, List<TicketEntity> tickets,
            List<UserEntity> technicians) {
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
        this.address = address;
        this.phone = phone;
        this.tickets = tickets;
        this.technicians = technicians;
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

    public List<TicketEntity> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketEntity> tickets) {
        this.tickets = tickets;
    }


    public List<UserEntity> getTechnicians() {
        return technicians;
    }


    public void setTechnicians(List<UserEntity> technicians) {
        this.technicians = technicians;
    }

    
}
