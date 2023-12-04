package com.example.PreEntrega.service;

import com.example.PreEntrega.entity.Invoice;
import com.example.PreEntrega.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<Invoice> findAll(){ return this.invoiceRepository.findAll();}

    public Invoice save(Invoice invoice){ return this.invoiceRepository.save(invoice);}

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
