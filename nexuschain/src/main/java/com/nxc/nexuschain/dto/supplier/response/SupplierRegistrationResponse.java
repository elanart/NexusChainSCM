package com.nxc.nexuschain.dto.supplier.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SupplierRegistrationResponse {
    private String id;
    private String fullName;
    private String email;
    private Boolean isConfirmed;
    private LocalDateTime createdDate;
}
