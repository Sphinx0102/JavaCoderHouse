package com.example.PreEntrega.service;

import com.example.PreEntrega.entity.Product;
import com.example.PreEntrega.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() { return this.productRepository.findAll();}
    public Product save(Product product) { return this.productRepository.save(product);}
    public Product findById(Integer id){
        var opClient = this.productRepository.findById(id);
        if (opClient.isPresent()){ return opClient.get();}
        else {return new Product();}
    }

    public String deleteById(Integer id) {
        productRepository.deleteById(id);
        return "Producto con ID " + id + " eliminado correctamente.";
    }

}
