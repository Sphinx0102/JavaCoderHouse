package com.example.PreEntrega.entity;

import lombok.Data;

import java.util.*;

@Data
public class InvoiceDDTO {
    private int stockProducts;
    private Product product;
    private double totalPrice;


    private InvoiceDDTO(){}
    public InvoiceDDTO(int stockProducts, Product product) {
        this.stockProducts = stockProducts;
        this.product = product;
    }

}
