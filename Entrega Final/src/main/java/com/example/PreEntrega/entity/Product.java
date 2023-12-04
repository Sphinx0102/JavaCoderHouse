package com.example.PreEntrega.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.util.*;

@Data
@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private String code;
    @Column(name="purchase_price")
    private double purchasePrice;
    @Column(name="sels_price")
    private double selsPrice;
    @Column(name="amount")
    private int stock;

}
