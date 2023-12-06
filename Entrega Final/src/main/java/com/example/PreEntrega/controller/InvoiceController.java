package com.example.PreEntrega.controller;

import com.example.PreEntrega.entity.Invoice;
import com.example.PreEntrega.entity.InvoiceDTO;
import com.example.PreEntrega.service.DetailsService;
import com.example.PreEntrega.service.InvoiceService;
import io.swagger.annotations.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
//@Api(tags = "Invoice API", description = "Endpoints relacionados con las facturas")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

//    @ApiOperation(value = "Obtener todas las facturas", response = List.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Facturas recuperadas exitosamente"),
//            @ApiResponse(code = 500, message = "Se produjo un error interno")
//    })
    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            List<Invoice> invoices = this.invoiceService.findAll();
            return ResponseEntity.ok(invoices);
        } catch (Exception e) {
            return handleException(e);
        }
    }

//    @ApiOperation(value = "Obtener una factura por ID", response = Invoice.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Factura recuperada exitosamente"),
//            @ApiResponse(code = 404, message = "Factura no encontrada"),
//            @ApiResponse(code = 500, message = "Se produjo un error interno")
//    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> one(@PathVariable Integer id) {
        try {
            Invoice invoice = this.invoiceService.findById(id);
            if (invoice != null) {
                return ResponseEntity.ok(invoice);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Factura no encontrada con ID " + id);
            }
        } catch (Exception e) {
            return handleException(e);
        }
    }

//    @ApiOperation(value = "Crear una nueva factura", response = Invoice.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Factura creada exitosamente"),
//            @ApiResponse(code = 400, message = "Stock no disponible"),
//            @ApiResponse(code = 404, message = "Producto no encontrado"),
//            @ApiResponse(code = 500, message = "Se produjo un error interno")
//    })
    @PostMapping
    public ResponseEntity<Object> newEntity(@RequestBody InvoiceDTO invoice) {
        try {
            Invoice savedInvoice = this.invoiceService.save(invoice);
            if (savedInvoice != null) {
                return ResponseEntity.ok(savedInvoice);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se produjo un error al procesar la factura.");
            }
        } catch (DetailsService.StockNotAvailableException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (DetailsService.ProductNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se produjo un error: " + e.getMessage());
        }
    }

//    @ApiOperation(value = "Actualizar una factura por ID", response = Invoice.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Factura actualizada exitosamente"),
//            @ApiResponse(code = 404, message = "Factura no encontrada"),
//            @ApiResponse(code = 500, message = "Se produjo un error interno")
//    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody InvoiceDTO invoiceDTO) {
        try {
            Invoice updatedInvoice = invoiceService.update(id, invoiceDTO);
            if (updatedInvoice != null) {
                return ResponseEntity.ok(updatedInvoice);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Factura no encontrada con ID " + id);
            }
        } catch (Exception e) {
            return handleException(e);
        }
    }

//    @ApiOperation(value = "Eliminar una factura por ID", response = String.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Factura eliminada exitosamente"),
//            @ApiResponse(code = 404, message = "Factura no encontrada"),
//            @ApiResponse(code = 500, message = "Se produjo un error interno")
//    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            this.invoiceService.deleteById(id);
            return ResponseEntity.ok("Factura con ID " + id + " eliminada correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Factura no encontrada con ID " + id);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    private ResponseEntity<Object> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se produjo un error: " + e.getMessage());
    }

//    @ApiModel(description = "Excepción de stock no disponible")
    public static class StockNotAvailableException extends RuntimeException {
        public StockNotAvailableException(String message) {
            super(message);
        }
    }

//    @ApiModel(description = "Excepción de creación de factura")
    public static class InvoiceCreationException extends RuntimeException {
        public InvoiceCreationException(String message) {
            super(message);
        }
    }

//    @ApiModel(description = "Excepción de producto no encontrado")
    public static class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }
}
