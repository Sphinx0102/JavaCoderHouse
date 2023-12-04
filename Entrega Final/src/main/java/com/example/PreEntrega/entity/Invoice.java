package com.example.PreEntrega.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name="clientId")
    private Client client;
    @CreatedDate
    @Column(name="enrollment_date")
    private Date enrollmentDate;

    private double total;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoiceDetails> invoicesDetails;
}
