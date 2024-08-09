package com.nxc.nexuschain.dto.user.response;

import com.nxc.nexuschain.dto.account.request.AccountRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailResponse {
    private UserResponse user;
    private AccountRequest account;
}
