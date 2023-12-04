package com.example.PreEntrega.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.*;

@Data
@Entity
@Table(name="details")
public class InvoiceDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="invoice_id")
    private Invoice invoice;
    @Column(name="amount")
    private int stockProducts;
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;
    @Column(name="total_price")
    private double totalPrice;


    public static List<InvoiceDetails> convertTo(List<InvoiceDDTO> invoiceDDTOList) {
        List<InvoiceDetails> convertedDetails = new ArrayList<>();

        for (InvoiceDDTO invoiceDDTO : invoiceDDTOList) {
            InvoiceDetails details = new InvoiceDetails();
            details.setStockProducts(invoiceDDTO.getStockProducts());

            details.setProduct(invoiceDDTO.getProduct());

            details.setTotalPrice(invoiceDDTO.getTotalPrice());

            convertedDetails.add(details);
        }

        return convertedDetails;
    }

}
