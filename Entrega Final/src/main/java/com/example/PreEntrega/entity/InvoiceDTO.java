package com.example.PreEntrega.entity;

import lombok.Data;

import java.util.*;

@Data
public class InvoiceDTO {
    private Client client;
    private Date enrollmentDate;
    private double total;
    private List<InvoiceDDTO> invoiceDetails;

    public InvoiceDTO(){}
    public InvoiceDTO(Client client, List<InvoiceDDTO> invoiceDetails) {
        this.client = client;
        this.enrollmentDate = new Date();
        this.invoiceDetails = invoiceDetails;

        // Calcular el total basado en los detalles de la factura
        this.total = calculateTotal();
    }

    private double calculateTotal() {
        double total = 0.0;

        if (invoiceDetails != null) {
            for (InvoiceDDTO invoiceDDTO : invoiceDetails) {
                total += invoiceDDTO.getTotalPrice();
            }
        }

        return total;
    }
}
