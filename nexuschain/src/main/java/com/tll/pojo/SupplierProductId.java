package com.tll.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class SupplierProductId implements java.io.Serializable {
    @Serial
    private static final long serialVersionUID = 945766549680177186L;
    @Column(name = "supplier_id", nullable = false, length = 50)
    private String supplierId;

    @Column(name = "product_id", nullable = false, length = 50)
    private String productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SupplierProductId entity = (SupplierProductId) o;
        return Objects.equals(this.supplierId, entity.supplierId) &&
                Objects.equals(this.productId, entity.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(supplierId, productId);
    }

}