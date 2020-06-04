package com.example.billetms.entities;


import lombok.*;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book  {

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



