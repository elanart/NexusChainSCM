package com.nxc.nexuschain.dto.user.request;

import com.nxc.nexuschain.dto.account.request.AccountRequest;
import com.nxc.nexuschain.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String fullName;
    private String address;
    private String phone;
    private String avatar;
    private String email;
    private RoleEnum role;
    private AccountRequest account;
}
