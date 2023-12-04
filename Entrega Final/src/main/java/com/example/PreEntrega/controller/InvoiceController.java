package com.example.PreEntrega.controller;

import com.example.PreEntrega.entity.Invoice;
import com.example.PreEntrega.entity.InvoiceDTO;
import com.example.PreEntrega.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public List<Invoice> findAll(){ return this.invoiceService.findAll();}
    @GetMapping("/{id}")
    public Invoice one(@PathVariable Integer id) { return this.invoiceService.findById(id);}
    @PostMapping
    public Invoice newEntity(@RequestBody InvoiceDTO invoice) { return this.invoiceService.save(invoice);}
    @PutMapping("/{id}")
    public Invoice update(@PathVariable Integer id, @RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.update(id, invoiceDTO);
    }
    @DeleteMapping
    public String delete(@PathVariable Integer id){ return this.invoiceService.deleteById(id);}
}
