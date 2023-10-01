package com.interzoid.sdk.model;

/**
 * The category used for generating the match key.
 */
public enum Category {
    COMPANY("company"),
    INDIVIDUAL("individual"),
    ADDRESS("address")
    ;

    private final String value;

    Category(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
