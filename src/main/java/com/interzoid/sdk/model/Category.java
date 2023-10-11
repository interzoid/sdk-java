package com.interzoid.sdk.model;

/**
 * 
 * The category used for generating the match key.
 */
public enum Category {
    /**
     * Use company name match algorithm.
     */
    COMPANY("company"),
    /**
     * Use person name match algorithm.
     */
    INDIVIDUAL("individual"),
    /**
     * Use address match algorithm.
     */
    ADDRESS("address");

    private final String value;

    /**
     * Constructs a new Category with the specified value.
     *
     * @param value
     */
    Category(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the Category.
     *
     * @return The value.
     */
    public String getValue() {
        return value;
    }
}
