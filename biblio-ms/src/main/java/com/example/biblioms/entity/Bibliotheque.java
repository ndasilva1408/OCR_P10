package com.example.biblioms.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Bibliotheque implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String adresse;
    String name;
    String phone;
}
