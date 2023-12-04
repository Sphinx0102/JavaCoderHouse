package com.example.PreEntrega.service;

import com.example.PreEntrega.entity.*;
import com.example.PreEntrega.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private DetailsRepository detailsRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<Invoice> findAll(){ return this.invoiceRepository.findAll();}

    public Invoice save(InvoiceDTO invoiceDTO) {
        Invoice invoice = new Invoice();
        invoice.setClient(invoiceDTO.getClient());
        invoice.setTotal(invoiceDTO.getTotal());
        invoice.setEnrollmentDate(invoiceDTO.getEnrollmentDate());

        List<InvoiceDDTO> invoiceDDTOList = invoiceDTO.getInvoiceDetails();
        if (invoiceDDTOList != null && !invoiceDDTOList.isEmpty()) {
            List<InvoiceDetails> invoiceDetailsList = InvoiceDetails.convertTo(invoiceDDTOList);
            for(InvoiceDetails invoiceDetails : invoiceDetailsList){
                invoiceDetails = validationProduct(invoiceDetails);
                if (invoiceDetails != null) {
                    detailsRepository.save(invoiceDetails);
                }
            }

        }

        return this.invoiceRepository.save(invoice);
    }


    public InvoiceDetails validationProduct(InvoiceDetails invoiceDetails) {
        if (invoiceDetails.getProduct() != null && invoiceDetails.getProduct().getId() != null) {
            Optional<Product> optionalProduct = productRepository.findById(invoiceDetails.getProduct().getId());

            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                int updatedStock = product.getStock() - invoiceDetails.getStockProducts();
                if (updatedStock >= 0) {
                    product.setStock(updatedStock);
                    productRepository.save(product);
                    return invoiceDetails;
                } else {
                    // En caso de que el producto no cuente con stock disponible
                    return null;
                }
            } else {
                // En caso de que el producto no se encuentre
                return null;
            }
        } else {
            // En caso de que el ID del producto sea nulo
            return null;
        }
    }

    public Invoice update(Integer id, InvoiceDTO invoiceDTO) {

        Optional<Invoice> optionalInvoice = this.invoiceRepository.findById(id);

        if (optionalInvoice.isPresent()) {
            Invoice existingInvoice = optionalInvoice.get();
            existingInvoice.setClient(invoiceDTO.getClient());
            existingInvoice.setTotal(invoiceDTO.getTotal());
            existingInvoice.setEnrollmentDate(invoiceDTO.getEnrollmentDate());

            // Actualizar la lista de InvoiceDetails
            List<InvoiceDetails> updatedInvoiceDetails = InvoiceDetails.convertTo(invoiceDTO.getInvoiceDetails());
            existingInvoice.setInvoicesDetails(updatedInvoiceDetails);

            return this.invoiceRepository.save(existingInvoice);
        } else {
            return null;
        }
    }

    public Invoice findById(Integer id){
            var opInvoice = this.invoiceRepository.findById(id);
            if(opInvoice.isPresent()){ return opInvoice.get();}
            else { return new Invoice();}

    }

    public String deleteById(Integer id){
        invoiceRepository.deleteById(id);
        return "Comprobante con ID " + id + " eliminado correctamente.";
    }

}
