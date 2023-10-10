package com.interzoid.sdk.model;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Interzoid
 * @version 1.1
 * Represents a request for calculating the match score between two full names.
 * This class is used to specify the two full names and the API key required for the request.
 * @see InterzoidRequest
 */
public class FullNameMatchScoreRequest extends InterzoidRequest {

    @NotBlank(message = "Value 1 is required")
    private final String value1;

    @NotBlank(message = "Value 2 is required")
    private final String value2;

    /**
     * Constructs a new FullNameMatchScoreRequest with the specified API key and two full names.
     *
     * @param apikey The API key required for making the request.
     * @param value1 The first full name used for calculating the match score.
     * @param value2 The second full name used for calculating the match score.
     */
    public FullNameMatchScoreRequest(String apikey, String value1, String value2) {
        super(apikey);
        this.value1 = value1;
        this.value2 = value2;
    }

    /**
     * @return The first full name.
     */
    public String getValue1() {
        return value1;
    }

    /**
     * @return The second full name.
     */
    public String getValue2() {
        return value2;
    }

    @Override
    public String toString() {
        return "FullNameMatchScoreRequest{" +
                "value1='" + value1 + '\'' +
                ", value2='" + value2 + '\'' +
                '}';
    }
}
