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
        this.invoiceDetails = invoiceDetails;

    }

}
