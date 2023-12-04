package com.example.PreEntrega.controller;

import com.example.PreEntrega.entity.Product;
import com.example.PreEntrega.entity.ProductDTO;
import com.example.PreEntrega.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> findAll(){ return this.productService.findAll();}
    @GetMapping("/{id}")
    public Product one(@PathVariable Integer id){ return this.productService.findById(id);}
    @PostMapping
    public Product newEntity(@RequestBody ProductDTO product){ return this.productService.save(product);}
    @PutMapping("/{id}")
    public Product update(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {
        return productService.update(id, productDTO);
    }
    @DeleteMapping
    public String delete(@PathVariable Integer id){ return this.productService.deleteById(id);}
}
