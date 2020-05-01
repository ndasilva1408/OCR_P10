package com.projet7.ZuulGateway.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Roles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Roles() {}

}
