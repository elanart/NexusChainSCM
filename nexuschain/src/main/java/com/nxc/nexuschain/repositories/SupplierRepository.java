package com.nxc.nexuschain.repositories;

import com.nxc.nexuschain.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
