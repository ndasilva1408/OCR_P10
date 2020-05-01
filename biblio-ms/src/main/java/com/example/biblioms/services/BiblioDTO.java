package com.example.biblioms.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class BiblioDTO {
    Long id;
    String adresse;
    String name;
    String phone;
}
