package com.nxc.nexuschain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nxc.nexuschain.enums.CriteriaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supplier")
public class Supplier implements Serializable {
    @Id
    private String id;

    @Column(name = "payment_terms", length = 300)
    private String paymentTerms;

    @JsonIgnore
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @MapsId
    @OneToOne(optional = false)
    private User user;

    @OneToOne(mappedBy = "supplier")
    private SupplierRating supplierRating;

    @JsonIgnore
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "supplier")
    private Set<SupplierProduct> supplierProducts;
}
