package com.example.PreEntrega.service;

import com.example.PreEntrega.entity.Product;
import com.example.PreEntrega.entity.ProductDTO;
import com.example.PreEntrega.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll() { return this.productRepository.findAll();}
    public Product save(ProductDTO product) {
        Product producto = new Product();
        producto.setCode(product.getCode());
        producto.setDescription(product.getDescription());
        producto.setStock(product.getStock());
        producto.setPurchasePrice(product.getPurchasePrice());
        producto.setSelsPrice(product.getSelsPrice());
        return this.productRepository.save(producto);}
    public Product findById(Integer id){
        var opClient = this.productRepository.findById(id);
        if (opClient.isPresent()){ return opClient.get();}
        else {return new Product();}
    }

    public String deleteById(Integer id) {
        productRepository.deleteById(id);
        return "Producto con ID " + id + " eliminado correctamente.";
    }

    public Product update(Integer id, ProductDTO productDTO) {
        Optional<Product> optionalProduct = this.productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product existingProduct = optionalProduct.get();
            existingProduct.setCode(productDTO.getCode());
            existingProduct.setDescription(productDTO.getDescription());
            existingProduct.setStock(productDTO.getStock());
            existingProduct.setPurchasePrice(productDTO.getPurchasePrice());
            existingProduct.setSelsPrice(productDTO.getSelsPrice());

            return this.productRepository.save(existingProduct);
        } else {
            return null;
        }
    }

}
