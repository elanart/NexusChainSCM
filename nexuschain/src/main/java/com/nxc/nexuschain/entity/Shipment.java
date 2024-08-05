package com.nxc.nexuschain.entity;

import com.nxc.nexuschain.enums.OrderStatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shipment")
public class Shipment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shipment_date", nullable = false)
    private LocalDateTime shipmentDate;

    @Enumerated(EnumType.STRING)
    private OrderStatusEnum status;

    @Column(name = "tracking_number", length = 100)
    private String trackingNumber;

    @Column(name = "expected_delivery")
    private LocalDateTime expectedDelivery;

    @ManyToOne(cascade = { CascadeType.PERSIST })
    @JoinColumn(name = "carrier_id")
    private Carrier carrier;
}
