package com.nxc.nexuschain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum GenderEnum {
    MALE("Nam"),
    FEMALE("Nữ"),
    OTHER("Khác");

    private final String displayName;
}
