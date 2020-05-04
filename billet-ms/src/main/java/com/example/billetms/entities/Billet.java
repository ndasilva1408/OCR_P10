package com.example.billetms.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Billet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bookerId;

    private String bookId;

    private Date bookingDate;

    private Date endDate;

    private Date extendDate;

    private String biblioId;

    @Column(name = "is_extend")
    private Boolean isExtend;

    @Column(name = "is_on_wait_list")
    private Boolean isOnWaitList;

}
