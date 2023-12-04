package com.example.PreEntrega.repository;

import com.example.PreEntrega.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
