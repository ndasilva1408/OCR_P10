package com.projet7.ZuulGateway.services;

import com.projet7.ZuulGateway.model.Roles;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Data
@Getter
@Setter
public class ClientDTO {
    private Long id;
    private String login;
    private String password;
    private Set<Roles> roles;
    private String adresse;
    private String mail;
    private String nom;
    private String prenom;
}
