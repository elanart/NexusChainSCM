package com.nxc.nexuschain.entities;

import com.nxc.nexuschain.enums.CriteriaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "supplier_rating")
public class SupplierRating implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Enumerated(EnumType.STRING)
    private CriteriaEnum criterion;

    @Column(precision = 3, scale = 2)
    private BigDecimal rating;

    @Column(length = 300)
    private String comment;

    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date", insertable = false)
    private LocalDateTime updatedDate;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Supplier supplier;

    @PrePersist
    protected void onCreate() {
        createdDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = LocalDateTime.now();
    }
}
