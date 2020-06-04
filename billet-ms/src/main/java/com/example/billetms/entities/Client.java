package com.example.billetms.entities;

import lombok.*;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private  Long id;

    String adresse;

    String login;

    String mail;

    String nom;

    String prenom;
}
