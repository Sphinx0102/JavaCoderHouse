package com.example.PreEntrega.controller;

import com.example.PreEntrega.entity.InvoiceDetails;
import com.example.PreEntrega.service.DetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/detail")
public class DetailsController {

    @Autowired
    private DetailsService detailsService;

    @GetMapping
    public List<InvoiceDetails> findAll(){ return this.detailsService.findAll();}
    @GetMapping("/{id}")
    public InvoiceDetails one(@PathVariable Integer id) { return this.detailsService.findById(id);}
    @PostMapping
    public InvoiceDetails newEntity(@RequestBody InvoiceDetails detail) { return this.detailsService.save(detail);}
    @DeleteMapping
    public String delete(@PathVariable Integer id){ return this.detailsService.deleteById(id);}
}
