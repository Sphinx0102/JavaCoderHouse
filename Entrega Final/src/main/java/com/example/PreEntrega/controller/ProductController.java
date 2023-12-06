package com.example.PreEntrega.controller;

import com.example.PreEntrega.entity.Product;
import com.example.PreEntrega.entity.ProductDTO;
import com.example.PreEntrega.service.ProductService;
import io.swagger.annotations.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
//@Api(tags = "Product API", description = "Endpoints relacionados con los Productos")
public class ProductController {

    @Autowired
    private ProductService productService;

//    @ApiOperation(value = "Obtener todos los productos", response = List.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Productos recuperados exitosamente"),
//            @ApiResponse(code = 500, message = "Se produjo un error interno")
//    })
    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            List<Product> products = this.productService.findAll();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return handleException(e);
        }
    }

//    @ApiOperation(value = "Obtener un producto por ID", response = Product.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Producto recuperado exitosamente"),
//            @ApiResponse(code = 404, message = "Producto no encontrado"),
//            @ApiResponse(code = 500, message = "Se produjo un error interno")
//    })
    @GetMapping("/{id}")
    public ResponseEntity<Object> one(@PathVariable Integer id) {
        try {
            Product product = this.productService.findById(id);
            if (product != null) {
                return ResponseEntity.ok(product);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado con ID " + id);
            }
        } catch (Exception e) {
            return handleException(e);
        }
    }

//    @ApiOperation(value = "Crear un nuevo producto", response = Product.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Producto creado exitosamente"),
//            @ApiResponse(code = 500, message = "Se produjo un error interno")
//    })
    @PostMapping
    public ResponseEntity<Object> newEntity(@RequestBody ProductDTO product) {
        try {
            Product savedProduct = this.productService.save(product);
            return ResponseEntity.ok(savedProduct);
        } catch (Exception e) {
            return handleException(e);
        }
    }

//    @ApiOperation(value = "Actualizar un producto por ID", response = Product.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Producto actualizado exitosamente"),
//            @ApiResponse(code = 404, message = "Producto no encontrado"),
//            @ApiResponse(code = 500, message = "Se produjo un error interno")
//    })
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Integer id, @RequestBody ProductDTO productDTO) {
        try {
            Product updatedProduct = productService.update(id, productDTO);
            if (updatedProduct != null) {
                return ResponseEntity.ok(updatedProduct);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado con ID " + id);
            }
        } catch (Exception e) {
            return handleException(e);
        }
    }

//    @ApiOperation(value = "Eliminar un producto por ID", response = String.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Producto eliminado exitosamente"),
//            @ApiResponse(code = 404, message = "Producto no encontrado"),
//            @ApiResponse(code = 500, message = "Se produjo un error interno")
//    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Integer id) {
        try {
            this.productService.deleteById(id);
            return ResponseEntity.ok("Producto con ID " + id + " eliminado correctamente.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado con ID " + id);
        } catch (Exception e) {
            return handleException(e);
        }
    }

    private ResponseEntity<Object> handleException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Se produjo un error: " + e.getMessage());
    }
}
