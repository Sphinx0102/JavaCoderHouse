package com.example.PreEntrega.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ClientDTO {
    private String name;
    private String lastName;
    private String dni;
    private Date birthdate;

    public ClientDTO(){}
    public ClientDTO(String name, String lastName, String dni, Date birthdate){
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.birthdate = birthdate;
    }


}
