package com.example.billetms.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Getter
@Setter
public class Book {
    Long id;
    Long provenance;
    String titre;
    Integer quantite;
    boolean disponible;
    String edition;
    String auteur;
    Integer anneeParution;
    String description;
    String urlimg;

}
