package com.example.clientms.services;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ClientDTO {
    private Long id;
    private String adresse;
    private String login;
    private String mail;
    private String nom;
    private String prenom;

}
