package com.nxc.nexuschain.repositories;

import com.nxc.nexuschain.entities.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
}
