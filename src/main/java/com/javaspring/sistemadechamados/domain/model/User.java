package com.javaspring.sistemadechamados.domain.model;

import java.util.List;
import java.util.UUID;

import com.javaspring.sistemadechamados.application.enums.Role;

public class User {
  private  UUID id;
  private String name;
  private String email;
  private String password;
  private Role role;
  private Company company;
  private List <Company> companies;
  private List <Ticket> ticketsCreated;
  private List <Ticket> ticketsAssigned;

  public User() {
  }

public User(UUID id, String name, String email, String password, Role role, Company company, List<Company> companies,
        List<Ticket> ticketsCreated, List<Ticket> ticketsAssigned) {
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

public Company getCompany() {
    return company;
}

public void setCompany(Company company) {
    this.company = company;
}

public List<Company> getCompanies() {
    return companies;
}

public void setCompanies(List<Company> companies) {
    this.companies = companies;
}

public List<Ticket> getTicketsCreated() {
    return ticketsCreated;
}

public void setTicketsCreated(List<Ticket> ticketsCreated) {
    this.ticketsCreated = ticketsCreated;
}

public List<Ticket> getTicketsAssigned() {
    return ticketsAssigned;
}

public void setTicketsAssigned(List<Ticket> ticketsAssigned) {
    this.ticketsAssigned = ticketsAssigned;
}
  

}
