package com.example.billetms.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Bibliotheque {
    Long id;
    String adresse;
    String name;
    String phone;
}
