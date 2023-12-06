package com.example.PreEntrega.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="details")
public class InvoiceDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="amount")
    private int stockProducts;
    @Column(name="total_price")
    private double totalPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "invoice_Id")
    @JsonIgnore
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_Id")
    private Product product;

    public InvoiceDetails() {
    }

    public InvoiceDetails(Invoice invoice, Product product, double totalPrice, int stockProducts) {
        this.invoice = invoice;
        this.product = product;
        this.totalPrice = totalPrice;
        this.stockProducts = stockProducts;
    }
}

