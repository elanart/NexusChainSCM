package com.nxc.nexuschain.dto.account.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
