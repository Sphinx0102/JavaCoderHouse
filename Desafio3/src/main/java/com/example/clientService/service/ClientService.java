package com.example.clientService.service;

import com.example.clientService.model.Client;
import com.example.clientService.repository.ClientRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public Client create(String name, String lastName, String fechaNacimiento){
        Client c = new Client();
        c.setName(name);
        c.setLastName(lastName);
        c.setFechaNacimiento(LocalDate.parse(fechaNacimiento));
        return this.clientRepository.save(c);
    }

    public String findById(long id){
        Optional<Client> cajaCliente = this.clientRepository.findById(id);
        if(cajaCliente.isPresent()){
            Client c = cajaCliente.get();

            String detailsJson = "{ \n" +
                                    "\"id\": " + c.getId() + "," +
                                    "\"name\": " + c.getName() + "," +
                                    "\"lastName\": " + c.getLastName() + "," +
                                    "\"Edad\": " + this.calcularEdad(c.getFechaNacimiento()) +
                                "}";

            return detailsJson;
        }

        return null;
    }
    public int calcularEdad(LocalDate fechaNacimiento) {

        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate nacimiento = LocalDate.parse(fechaNacimiento.toString(), formatter);

        Period period = Period.between(nacimiento, fechaActual);
        int edad = period.getYears();


        return edad;
    }

}
