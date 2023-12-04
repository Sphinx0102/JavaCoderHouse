package com.example.PreEntrega.entity;

import jakarta.persistence.*;

@Entity
@Table(name="details")
public class InvoiceDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="voucherId")
    private Invoice invoice;
    @Column(name="amount")
    private int stockProducts;
    @ManyToOne
    @JoinColumn(name="productId")
    private Product product;
    private double totalPrice;

}
