package com.example.PreEntrega.controller;

import com.example.PreEntrega.entity.Client;
import com.example.PreEntrega.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> findAll() { return this.clientService.findAll(); }
    @GetMapping("/{id}")
    public Client one(@PathVariable Integer id){ return this.clientService.findById(id);}
    @PostMapping
    public Client newEntity(@RequestBody Client client){ return this.clientService.save(client);}
    @DeleteMapping
    public String delete(@PathVariable Integer id){ return this.clientService.deleteById(id);}
}
