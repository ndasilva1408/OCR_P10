package com.projet7.ZuulGateway.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter

@Entity

public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "clients_roles",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Roles> roles;

    @Column(name = "adresse")
    String adresse;
    @Column(name = "mail")
    String mail;
    @Column(name = "nom")
    String nom;
    @Column(name = "prenom")
    String prenom;
}

