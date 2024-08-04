package com.nxc.nexuschain.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "invoice")
public class Invoice {
    @Id
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @Column(name = "invoice_date", nullable = false)
    private Instant invoiceDate;

    @Column(name = "total_amount", precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @ColumnDefault("0")
    @Column(name = "paid")
    private Boolean paid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tax_id")
    private TaxRate tax;

    @OneToOne(mappedBy = "invoice")
    private PurchaseOrder purchaseOrder;

    @OneToOne(mappedBy = "invoice")
    private SaleOrder saleOrder;

}