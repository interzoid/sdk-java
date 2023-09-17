package com.interzoid.sdk.model;

import jakarta.validation.constraints.NotBlank;

/**
 * Represents a request structure for obtaining a matching score between two organization values.
 * This class extends the InterzoidRequest class and includes two values (Value 1 and Value 2) to be used for scoring.
 */
public class OrganizationMatchScoreRequest extends InterzoidRequest {
    /**
     * The first organization value used for scoring (Value 1).
     */
    @NotBlank(message = "Value 1 is required")
    private final String value1;

    /**
     * The second organization value used for scoring (Value 2).
     */
    @NotBlank(message = "Value 2 is required")
    private final String value2;

    /**
     * Constructs a new OrganizationMatchScoreRequest object with the specified API key, Value 1, and Value 2.
     *
     * @param apikey  The API key required for authentication.
     * @param value1  The first organization value (Value 1) to be used for scoring.
     * @param value2  The second organization value (Value 2) to be used for scoring.
     */
    public OrganizationMatchScoreRequest(String apikey, String value1, String value2) {
        super(apikey);
        this.value1 = value1;
        this.value2 = value2;
    }

    /**
     * Gets the first organization value (Value 1).
     *
     * @return The first organization value.
     */
    public String getValue1() {
        return value1;
    }

    /**
     * Gets the second organization value (Value 2).
     *
     * @return The second organization value.
     */
    public String getValue2() {
        return value2;
    }

    /**
     * Returns a string representation of the OrganizationMatchScoreRequest object.
     *
     * @return A string containing Value 1, Value 2, and the API key (inherited from InterzoidRequest).
     */
    @Override
    public String toString() {
        return "OrganizationMatchScoreRequest{" +
                "value1='" + value1 + '\'' +
                ", value2='" + value2 + '\'' +
                "} " + super.toString();
    }
}
