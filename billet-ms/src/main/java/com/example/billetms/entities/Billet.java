package com.example.billetms.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Billet  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookerId;

    private String bookId;

    private Date bookingDate;

    private Date endDate;

    private Date extendDate;
    @Column(name = "biblio_id")
    private String biblioId;

    @Column(name = "is_extend")
    private Boolean isExtend;

      @Column(name = "is_on_wait_list")
    private Boolean isOnWaitList;

    @Column(name = "is_extendable")
    private Boolean isExtendable;

    // LIMIT DATE , set au moment de la réception du mail pour l'attente. DE PLUS , A check dans un autre batch, que la date d'autjourd'hui plus petite que limitDate , SINON SUPPR RESA .
    @Column(name = "limit_date")
    private Date limitDate;







}
