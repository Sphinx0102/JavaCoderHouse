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
        this.totalPrice = calculateTotalPrice();
    }

    // Calcular el totalPrice multiplicando el stock del producto por el precio de venta
    private double calculateTotalPrice() {
        if (this.totalPrice != 0.0) {
            return this.totalPrice;
        } else {
            return stockProducts * product.getSelsPrice();
        }
    }
}
