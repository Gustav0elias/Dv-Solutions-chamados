package com.javaspring.sistemadechamados.infrastructure.persistence.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.javaspring.sistemadechamados.application.enums.Role;
import com.javaspring.sistemadechamados.domain.model.Company;
import com.javaspring.sistemadechamados.domain.model.Ticket;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_tb")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;
  @Column(nullable = false)
  private String name;
  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private String password;
  
  @Enumerated(EnumType.STRING)
  private Role role;
  @ManyToOne
  @JoinColumn(name = "company_id")
  private CompanyEntity company;
  @ManyToMany(mappedBy = "technicians") 
    private List<CompanyEntity> companies = new ArrayList<>();
  @OneToMany(mappedBy = "createdByUser")
  private List <TicketEntity> ticketsCreated = new ArrayList<>();
  @OneToMany(mappedBy = "responsibleUser")
  private List <TicketEntity> ticketsAssigned = new ArrayList<>();
  
  public UserEntity() {
  }

public UserEntity(UUID id, String name, String email, String password, Role role, CompanyEntity company,
        List<CompanyEntity> companies, List<TicketEntity> ticketsCreated, List<TicketEntity> ticketsAssigned) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.role = role;
    this.company = company;
    this.companies = companies;
    this.ticketsCreated = ticketsCreated;
    this.ticketsAssigned = ticketsAssigned;
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

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

public String getPassword() {
    return password;
}

public void setPassword(String password) {
    this.password = password;
}

public Role getRole() {
    return role;
}

public void setRole(Role role) {
    this.role = role;
}

public CompanyEntity getCompany() {
    return company;
}

public void setCompany(CompanyEntity company) {
    this.company = company;
}

public List<CompanyEntity> getCompanies() {
    return companies;
}

public void setCompanies(List<CompanyEntity> companies) {
    this.companies = companies;
}

public List<TicketEntity> getTicketsCreated() {
    return ticketsCreated;
}

public void setTicketsCreated(List<TicketEntity> ticketsCreated) {
    this.ticketsCreated = ticketsCreated;
}

public List<TicketEntity> getTicketsAssigned() {
    return ticketsAssigned;
}

public void setTicketsAssigned(List<TicketEntity> ticketsAssigned) {
    this.ticketsAssigned = ticketsAssigned;
}
  

}
