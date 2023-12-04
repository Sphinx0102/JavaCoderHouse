package com.example.PreEntrega.service;

import com.example.PreEntrega.entity.Client;
import com.example.PreEntrega.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll(){ return this.clientRepository.findAll();}
    public Client save(Client client){ return this.clientRepository.save(client);}
    public Client findById(Integer id){
        var opClient = this.clientRepository.findById(id);
        if(opClient.isPresent()){ return opClient.get();}
        else { return new Client();}
    }
    public String deleteById(Integer id) {
        clientRepository.deleteById(id);
        return "Cliente con ID " + id + " eliminado correctamente.";
    }

}
