package com.example.PreEntrega.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ProductDTO {
    private String description;
    private String code;
    private double purchasePrice;
    private double selsPrice;
    private int stock;

    private ProductDTO(){}

    private ProductDTO(String description, String code, double purchasePrice, double selsPrice, int stock){
        this.description = description;
        this.code = code;
        this.purchasePrice = purchasePrice;
        this.selsPrice = selsPrice;
        this.stock = stock;
    }
}
