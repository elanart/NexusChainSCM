package com.nxc.nexuschain.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "supplier_rating")
public class SupplierRating {
    @Id
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @Column(name = "review_date")
    private Instant reviewDate;

    @Column(name = "criteria", length = 100)
    private String criteria;

    @Column(name = "rating", precision = 3, scale = 2)
    private BigDecimal rating;

    @Lob
    @Column(name = "comments")
    private String comments;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

}