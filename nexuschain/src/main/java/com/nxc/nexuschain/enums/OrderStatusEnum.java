package com.nxc.nexuschain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderStatusEnum {
    PENDING,
    CONFIRMED,
    COMPLETED,
    CANCELED;
}
