package com.nxc.nexuschain.dtos;

import com.nxc.nexuschain.enums.RoleEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String fullName;

    private String address;

    private String phone;

    private String email;

    private RoleEnum role;

    private String paymentTerms;

    private String cooperationTerms;
}
