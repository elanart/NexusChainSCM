package com.nxc.nexuschain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ShippingStatus {
    SHIPPED,
    IN_TRANSIT,
    DELIVERED,
    RETURNED;
}
