package com.example.billetms.entities;

import lombok.Data;

import java.util.Date;

@Data
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
