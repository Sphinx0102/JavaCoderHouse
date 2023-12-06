package com.example.PreEntrega.service;

import com.example.PreEntrega.controller.InvoiceController;
import com.example.PreEntrega.entity.*;
import com.example.PreEntrega.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.PreEntrega.service.WorldClockService.WorldClockResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private DetailsService detailsService;
    @Autowired
    private ClientRepository clientRepository;


    public List<Invoice> findAll(){ return this.invoiceRepository.findAll();}

    public Invoice save(InvoiceDTO invoiceDTO) {
        Invoice invoice = new Invoice();
        Client client = this.clientService.findById(invoiceDTO.getClient().getId());
        invoice.setClient(client);

        try {
            // Paso 1: Guardar el Invoice
            invoice = this.invoiceRepository.save(invoice);
            this.invoiceRepository.flush();

            // Paso 2: Guardar los detalles y establecer la relación con el Invoice
            List<InvoiceDetails> invoiceDetails = detailsService.save(invoice, invoiceDTO.getInvoiceDetails());
            if (invoiceDetails == null) {
                rollbackInvoiceCreation(invoice);
                throw new InvoiceController.InvoiceCreationException("No se pudieron guardar los detalles de la factura.");
            }

            TotalSummary totalCalculated = calculateTotal(invoiceDetails);
            invoice.setTotal(totalCalculated.getTotal());
            invoice.setTotalStockProduct(totalCalculated.getTotalProduct());
            invoice.setInvoicesDetails(invoiceDetails);

            // Paso 2.1: Obtener la hora actual de la API de WorldClock
            String worldClockApiUrl = "http://worldclockapi.com/api/json/utc/now";
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<WorldClockResponse> response = restTemplate.getForEntity(worldClockApiUrl, WorldClockResponse.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                WorldClockResponse worldClockResponse = response.getBody();
                Date currentTime = worldClockResponse.getCurrentDateTime();
                invoice.setEnrollmentDate(currentTime);
            } else {
                Date date = new Date();
                invoice.setEnrollmentDate(date);
            }

            // Paso 3: Actualizar el Invoice después de guardar los detalles
            this.invoiceRepository.save(invoice);
            this.invoiceRepository.flush();

            return invoice;

        } catch (Exception e) {
            rollbackInvoiceCreation(invoice);
            throw new InvoiceController.InvoiceCreationException("Error al crear la factura: " + e.getMessage());
        }
    }

    private void rollbackInvoiceCreation(Invoice invoice) {
        if (invoice != null && invoice.getId() != null) {
            this.invoiceRepository.deleteById(invoice.getId());
        }
    }

    @Data
    @AllArgsConstructor
    class TotalSummary {
        private double total;
        private int totalProduct;

        public TotalSummary() {
        }
    }


    public TotalSummary calculateTotal(List<InvoiceDetails> invoiceDetails) {
        double total = 0.0;
        int totalProduct = 0;

        if (!invoiceDetails.isEmpty()) {
            for (InvoiceDetails invoiceD : invoiceDetails) {
                total += invoiceD.getTotalPrice();
                totalProduct += invoiceD.getStockProducts();
            }
        }

        return new TotalSummary(total, totalProduct);
    }


    public Invoice update(Integer id, InvoiceDTO invoiceDTO) {

        Optional<Invoice> optionalInvoice = this.invoiceRepository.findById(id);

        if (optionalInvoice.isPresent()) {
            Invoice existingInvoice = optionalInvoice.get();
            existingInvoice.setClient(invoiceDTO.getClient());
            existingInvoice.setTotal(invoiceDTO.getTotal());
            existingInvoice.setEnrollmentDate(invoiceDTO.getEnrollmentDate());

            // Actualizar la lista de InvoiceDetails
            List<InvoiceDetails> updatedInvoiceDetails = detailsService.convertTo(invoiceDTO.getInvoiceDetails());
            existingInvoice.setInvoicesDetails(updatedInvoiceDetails);

            return this.invoiceRepository.save(existingInvoice);
        } else {
            return null;
        }
    }

    public Invoice findById(Integer id){
            var opInvoice = this.invoiceRepository.findById(id);
            if(opInvoice.isPresent()){ return opInvoice.get();}
            else { return null;}

    }

    public void deleteById(Integer id){
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        if(optionalInvoice.isPresent()){
            invoiceRepository.deleteById(id);
        }
        else {
            throw new EntityNotFoundException("Producto no encontrado con ID " + id);
        }
    }

}
