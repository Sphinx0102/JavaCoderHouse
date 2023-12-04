package com.example.PreEntrega.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;
    private Date created_ad;
    private double total;
}
