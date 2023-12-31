package com.example.clientService.controller;

import com.example.clientService.model.Client;
import com.example.clientService.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path="api/clients")
@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;
    @PostMapping("/")
    public ResponseEntity<Client> create (@RequestParam String name, @RequestParam String lastName, @RequestParam String fechaNacimiento){
        return new ResponseEntity<>( this.clientService.create(name, lastName, fechaNacimiento), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return new ResponseEntity<>(this.clientService.findById(id), HttpStatus.OK);
    }
}
