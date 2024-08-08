package com.nxc.nexuschain.services;

import com.nxc.nexuschain.dto.supplier.request.SupplierRegistrationRequest;
import com.nxc.nexuschain.dto.supplier.response.SupplierRegistrationResponse;

public interface SupplierService {
    SupplierRegistrationResponse registerSupplier(SupplierRegistrationRequest request);
}
