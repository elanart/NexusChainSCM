package com.nxc.nexuschain.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private String fullName;
    private String email;
    private String address;
    private String phone;
    private String avatar;
    private Boolean isConfirmed;
    private LocalDateTime createdDate;
}
