package org.nopeCommerce_ui.constants;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@ToString
public enum Currencies {
    US_DOLLAR("US Dollar"),
    EURO("Euro");

    private final String name;
}
