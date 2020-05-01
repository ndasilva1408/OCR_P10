package com.example.clientms.entity;

import lombok.*;

import javax.persistence.*;

@Entity

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    @Column(name = "adresse")
    String adresse;
    @Column(name = "login")
    String login;
    @Column(name = "mail")
    String mail;
    @Column(name = "nom")
    String nom;
    @Column(name = "prenom")
    String prenom;
}
