package com.example.billetms.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    //User
    private String nom;
    private String prenom;
    private String emailClient;

    // Book
    private String bookTitle;

    // Borrow
    private Boolean isExtend;
    private Date endDate;
    private Date extendDate;


}
