package com.nxc.nexuschain.controllers;

import com.nxc.nexuschain.dto.supplier.request.SupplierRegistrationRequest;
import com.nxc.nexuschain.dto.supplier.response.SupplierRegistrationResponse;
import com.nxc.nexuschain.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @PostMapping("/register")
    public ResponseEntity<SupplierRegistrationResponse> registerSupplier(@RequestBody SupplierRegistrationRequest request) {
        SupplierRegistrationResponse response = supplierService.registerSupplier(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
