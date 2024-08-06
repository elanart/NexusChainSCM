package com.nxc.nexuschain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nxc.nexuschain.enums.CriteriaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supplier")
public class Supplier implements Serializable {
    @Id
    private Long id;

    @Column(name = "payment_terms", length = 300)
    private String paymentTerms;

    @Column(name = "review_date", updatable = false)
    private LocalDateTime reviewDate;

    @Enumerated(EnumType.STRING)
    private CriteriaEnum criterion;

    @Column(precision = 3, scale = 2)
    private BigDecimal rating;

    @Column(length = 300)
    private String comment;

    @JsonIgnore
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @MapsId
    @OneToOne(optional = false)
    private User user;

    @JsonIgnore
    @OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, mappedBy = "supplier")
    private Set<SupplierProduct> supplierProducts;
}
