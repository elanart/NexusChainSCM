package com.nxc.nexuschain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "invoice_date")
    private LocalDateTime invoiceDate;

    @Column(name = "total_amount", precision = 10, scale = 2)
    private BigDecimal totalAmount;

    private Boolean paid;

    @ManyToOne(cascade = { CascadeType.PERSIST })
    @JoinColumn(name = "tax_id", referencedColumnName = "id")
    private Tax tax;

    @ManyToOne(cascade = { CascadeType.PERSIST })
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;
}
