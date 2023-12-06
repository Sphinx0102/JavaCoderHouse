package com.example.PreEntrega.service;

import com.example.PreEntrega.entity.Invoice;
import com.example.PreEntrega.entity.InvoiceDDTO;
import com.example.PreEntrega.entity.InvoiceDetails;
import com.example.PreEntrega.entity.Product;
import com.example.PreEntrega.repository.DetailsRepository;
import com.example.PreEntrega.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DetailsService {

    @Autowired
    private DetailsRepository detailsRepository;

    @Autowired
    private ProductRepository productRepository;


    public List<InvoiceDetails> save(Invoice invoice, List<InvoiceDDTO> invoiceDDTOList) {
        List<InvoiceDetails> invoiceDetailsPartial = convertTo(invoiceDDTOList);
        List<InvoiceDetails> invoiceDetails = new ArrayList<>();

        try {
            for (InvoiceDetails invoiceD : invoiceDetailsPartial) {
                invoiceD = validationProduct(invoiceD);
                if (invoiceD != null) {
                    // Establece la referencia a Invoice al momento de la creación
                    invoiceDetails.add(
                            new InvoiceDetails(invoice, invoiceD.getProduct(), invoiceD.getTotalPrice(), invoiceD.getStockProducts())
                    );
                }
                else {return null;}
            }


            detailsRepository.saveAll(invoiceDetails);
            detailsRepository.flush();

        } catch (StockNotAvailableException | ProductNotFoundException | IllegalArgumentException e) {
            // Lanza excepciones específicas que se propagarán al controlador
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al procesar los detalles de la factura. Detalles: " + e.getMessage(), e);
        }

        return invoiceDetails;
    }

    public InvoiceDetails validationProduct(InvoiceDetails invoiceDetails) {
        if (invoiceDetails.getProduct() != null && invoiceDetails.getProduct().getId() != null) {
            Optional<Product> optionalProduct = productRepository.findById(invoiceDetails.getProduct().getId());

            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                int updatedStock = product.getStock() - invoiceDetails.getStockProducts();

                if (updatedStock >= 0) {
                    product.setStock(updatedStock);
                    invoiceDetails.setProduct(product);
                    productRepository.save(product);

                    // Calcula el total si no se proporciona un valor
                    double totalPrice = (invoiceDetails.getTotalPrice() != 0.0) ?
                            invoiceDetails.getTotalPrice() :
                            invoiceDetails.getStockProducts() * product.getSelsPrice();

                    invoiceDetails.setTotalPrice(totalPrice);

                    return invoiceDetails;
                } else {
                    throw new StockNotAvailableException("No hay suficiente stock disponible para el producto.");
                }
            } else {
                throw new ProductNotFoundException("El producto no se encuentra en la base de datos.");
            }
        } else {
            throw new IllegalArgumentException("ID del producto nulo o inválido.");
        }
    }

    public static List<InvoiceDetails> convertTo(List<InvoiceDDTO> invoiceDDTOList) {
        List<InvoiceDetails> convertedDetails = new ArrayList<>();

        for (InvoiceDDTO invoiceDDTO : invoiceDDTOList) {
            InvoiceDetails details = new InvoiceDetails();
            details.setStockProducts(invoiceDDTO.getStockProducts());

            details.setProduct(invoiceDDTO.getProduct());

            details.setTotalPrice(invoiceDDTO.getTotalPrice());

            convertedDetails.add(details);
        }

        return convertedDetails;
    }

    public class StockNotAvailableException extends RuntimeException {
        public StockNotAvailableException(String message) {
            super(message);
        }
    }

    public class ProductNotFoundException extends RuntimeException {
        public ProductNotFoundException(String message) {
            super(message);
        }
    }


}
