package com.example.clientService.repository;

import com.example.clientService.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long > {


}
