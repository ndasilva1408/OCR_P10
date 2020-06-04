package com.example.billetms.entities;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bibliotheque {
    Long id;
    String adresse;
    String name;
    String phone;
}
