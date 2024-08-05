package com.nxc.nexuschain.entity;

import com.nxc.nexuschain.enums.OrderTypeEnum;
import com.nxc.nexuschain.enums.ShippingStatus;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private ShippingStatus status;

    @Enumerated(EnumType.STRING)
    private OrderTypeEnum type;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, mappedBy = "order")
    private Set<Invoice> invoices;

    @Valid
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, mappedBy = "order")
    private OrderDetail orderDetail;
}
