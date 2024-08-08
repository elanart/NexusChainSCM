package com.nxc.nexuschain.dto.supplier.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplierRegistrationRequest {
    private String fullName;
    private String address;
    private String phone;
    private String avatar;
    private String email;
    private String username;
    private String password;
    private String paymentTerms;
}
