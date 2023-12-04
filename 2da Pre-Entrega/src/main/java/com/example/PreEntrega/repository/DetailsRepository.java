package com.example.PreEntrega.repository;

import com.example.PreEntrega.entity.InvoiceDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailsRepository extends JpaRepository<InvoiceDetails, Integer> {
}
