package com.example.PreEntrega.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.util.*;

@Entity
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private String code;
    private double purchasePrice;
    private double selsPrice;
    @Column(name="amount")
    private int stock;
    @CreatedDate
    private Date enrollmentDate;


}
