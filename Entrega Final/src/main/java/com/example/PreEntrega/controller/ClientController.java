package com.example.PreEntrega.controller;

import com.example.PreEntrega.entity.Client;
import com.example.PreEntrega.entity.ClientDTO;
import com.example.PreEntrega.service.ClientService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
/*@Api(tags = "Client API", description = "Endpoints relacionados con los Clientes")*/
public class ClientController {

    @Autowired
    private ClientService clientService;

//    @ApiOperation(value = "Obtener todos los clientes", response = List.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Clientes recuperados exitosamente"),
//            @ApiResponse(code = 500, message = "Se produjo un error interno")
//    })
    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            List<Client> clients = this.clientService.findAll();
            return ResponseEntity.ok(clients);
        } catch (Exception e) {
            return handleException(e);
        }
    }

//    @ApiOperation(value = "Obtener un cliente por ID", response = Client.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Cliente recuperado exitosamente"),
//            @ApiResponse(code = 404, message = "Cliente no encontrado"),
//            @ApiResponse(code = 500, message = "Se produjo un error interno")
//    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> one(@PathVariable Integer id) {
        try {
            Client client = this.clientService.findById(id);
            if (client != null) {
                return ResponseEntity.ok(client);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con ID " + id);
            }
        } catch (Exception e) {
            return handleException(e);
        }
    }

//    @ApiOperation(value = "Crear un nuevo cliente", response = Client.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Cliente creado exitosamente"),
//            @ApiResponse(code = 500, message = "Se produjo un error interno")
//    })
    @PostMapping
    public ResponseEntity<Object> newEntity(@RequestBody ClientDTO client) {
        try {
            Client savedClient = this.clientService.save(client);
            return ResponseEntity.ok(savedClient);
        } catch (Exception e) {
            return handleException(e);
        }
    }

//    @ApiOperation(value = "Actualizar un cliente por ID", response = Client.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Cliente actualizado exitosamente"),
//            @ApiResponse(code = 404, message = "Cliente no encontrado"),
//            @ApiResponse(code = 500, message = "Se produjo un error interno")
//    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody ClientDTO clientDTO) {
        try {
            Client updatedClient = clientService.update(id, clientDTO);
            if (updatedClient != null) {
                return ResponseEntity.ok(updatedClient);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con ID " + id);
            }
        } catch (Exception e) {
            return handleException(e);
        }
    }

//    @ApiOperation(value = "Eliminar un cliente por ID", response = String.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Cliente eliminado exitosamente"),
//            @ApiResponse(code = 404, message = "Cliente no encontrado"),
//            @ApiResponse(code = 500, message = "Se produjo un error interno")
//    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            this.clientService.deleteById(id);
            return ResponseEntity.ok("Cliente con ID " + id + " eliminado correctamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con ID " + id);
        }
    }

    private ResponseEntity<Object> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se produjo un error: " + e.getMessage());
    }
}
