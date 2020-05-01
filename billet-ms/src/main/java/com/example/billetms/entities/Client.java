package com.example.billetms.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class Client {
    private  Long id;

    String adresse;

    String login;

    String mail;

    String nom;

    String prenom;
}
