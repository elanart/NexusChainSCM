package com.nxc.nexuschain.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "shipment")
public class Shipment {
    @Id
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @Column(name = "shipment_date", nullable = false)
    private Instant shipmentDate;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "tracking_number", length = 100)
    private String trackingNumber;

    @Column(name = "expected_delivery")
    private Instant expectedDelivery;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private ShipmentCompany company;

    @OneToOne(mappedBy = "shipment")
    private PurchaseOrder purchaseOrder;

    @OneToOne(mappedBy = "shipment")
    private SaleOrder saleOrder;

}