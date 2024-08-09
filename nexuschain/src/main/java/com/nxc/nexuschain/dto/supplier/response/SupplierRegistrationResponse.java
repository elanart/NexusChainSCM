package com.nxc.nexuschain.dto.supplier.response;

import com.nxc.nexuschain.dto.user.response.UserResponse;
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
    private UserResponse user;
}
