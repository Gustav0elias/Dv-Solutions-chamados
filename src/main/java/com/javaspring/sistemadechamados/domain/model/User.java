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

}
