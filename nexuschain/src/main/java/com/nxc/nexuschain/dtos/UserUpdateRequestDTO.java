package com.nxc.nexuschain.dtos;

import com.nxc.nexuschain.enums.RoleEnum;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestDTO {
    private Long id;

    private String username;

    private String password;

    private String fullName;

    private String email;

    private RoleEnum role;

    private String paymentTerms;

    private String cooperationTerms;

}
