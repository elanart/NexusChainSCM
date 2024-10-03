package com.nxc.nexuschain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "supplier")
public class Supplier {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    private User user;

    @Lob
    @Column(name = "payment_terms")
    private String paymentTerms;

    @OneToMany(mappedBy = "supplier")
    private Set<Pricing> pricings = new LinkedHashSet<>();

    @OneToMany(mappedBy = "supplier")
    private Set<SupplierRating> supplierRatings = new LinkedHashSet<>();

}