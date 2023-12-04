package com.example.PreEntrega.service;

import com.example.PreEntrega.entity.Client;
import com.example.PreEntrega.entity.ClientDTO;
import com.example.PreEntrega.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findAll(){ return this.clientRepository.findAll();}
    public Client save(ClientDTO client){
        Client cliente = new Client();
        cliente.setName(client.getName());
        cliente.setLastName(client.getLastName());
        cliente.setDni(client.getDni());
        cliente.setBirthdate(client.getBirthdate());
        return this.clientRepository.save(cliente);

    }
    public Client update(Integer id, ClientDTO clientDTO) {
        Optional<Client> optionalClient = this.clientRepository.findById(id);

        if (optionalClient.isPresent()) {
            Client existingClient = optionalClient.get();
            existingClient.setName(clientDTO.getName());
            existingClient.setLastName(clientDTO.getLastName());
            existingClient.setDni(clientDTO.getDni());
            existingClient.setBirthdate(clientDTO.getBirthdate());
            return this.clientRepository.save(existingClient);
        } else {
            return null;
        }
    }
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
