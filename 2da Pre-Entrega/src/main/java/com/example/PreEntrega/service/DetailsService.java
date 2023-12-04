package com.example.PreEntrega.service;

import com.example.PreEntrega.entity.InvoiceDetails;
import com.example.PreEntrega.repository.DetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DetailsService {

    @Autowired
    private DetailsRepository detailsRepository;

    public List<InvoiceDetails> findAll(){ return this.detailsRepository.findAll();}
    public InvoiceDetails save(InvoiceDetails detail){ return this.detailsRepository.save(detail);}
    public InvoiceDetails findById(Integer id){
        var opDetail= this.detailsRepository.findById(id);
        if(opDetail.isPresent()){ return opDetail.get();}
        else { return new InvoiceDetails();}
    }
    public String deleteById(Integer id) {
        detailsRepository.deleteById(id);
        return "Detalle de comprobante con ID " + id + " eliminado correctamente.";
    }

}