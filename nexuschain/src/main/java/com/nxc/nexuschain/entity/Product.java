package com.nxc.nexuschain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    private String description;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer quantity;

    private Boolean isDeleted;

    @ManyToOne(cascade = { CascadeType.PERSIST })
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, mappedBy = "product")
    private Set<SupplierProduct> supplierProducts;

    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, mappedBy = "product")
    private Set<OrderDetail> orderDetails;

//    @OneToMany(mappedBy = "product")
//    private Set<Inventory> inventories = new LinkedHashSet<>();
//
//    @OneToMany(mappedBy = "product")
//    private Set<Pricing> pricings = new LinkedHashSet<>();
//
//    @OneToMany(mappedBy = "product")
//    private Set<PurchaseOrderDetail> purchaseOrderDetails = new LinkedHashSet<>();
//
//    @OneToMany(mappedBy = "product")
//    private Set<SaleOrderDetail> saleOrderDetails = new LinkedHashSet<>();
}
