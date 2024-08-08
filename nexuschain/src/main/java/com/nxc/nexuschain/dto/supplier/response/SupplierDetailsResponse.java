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
public class SupplierDetailsResponse {
    private String id;
    private String fullName;
    private String email;
    private String address;
    private String phone;
    private String avatar;
    private String paymentTerms;
    private LocalDateTime createdDate;
}
