package com.example.PreEntrega.entity;

import jakarta.persistence.*;

@Entity
@Table(name="invoices_details")
public class InvoiceDetails {
    private Integer id;
    @ManyToOne
    @JoinColumn(name="invoice_id")
    private Invoice invoice;
    @Column(name="amount")
    private int stockProducts;
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
    private double price;

}
