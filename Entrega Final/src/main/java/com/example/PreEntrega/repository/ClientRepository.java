package com.example.PreEntrega.repository;

import com.example.PreEntrega.entity.Client;
import com.example.PreEntrega.entity.ClientDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
