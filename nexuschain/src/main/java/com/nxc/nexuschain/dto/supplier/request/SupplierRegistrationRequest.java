package com.nxc.nexuschain.dto.supplier.request;

import com.nxc.nexuschain.dto.user.request.UserRequest;
import com.nxc.nexuschain.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplierRegistrationRequest {
    private UserRequest user;
    private String paymentTerms;
}
