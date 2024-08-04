package com.nxc.nexuschain.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "shipment_company")
public class ShipmentCompany {
    @Id
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "contact_info")
    private String contactInfo;

    @ColumnDefault("1")
    @Column(name = "active")
    private Boolean active;

    @Column(name = "rating", precision = 5, scale = 2)
    private BigDecimal rating;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "company")
    private Set<Shipment> shipments = new LinkedHashSet<>();

}