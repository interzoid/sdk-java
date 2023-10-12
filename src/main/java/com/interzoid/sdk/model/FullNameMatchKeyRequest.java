package com.interzoid.sdk.model;

import jakarta.validation.constraints.NotBlank;

/**
 * Represents a request for generating a full name match key, used for comparing and sorting full names.
 * This class is used to specify the full name and API key required for the request.
 * @see InterzoidRequest
 */
public class FullNameMatchKeyRequest extends InterzoidRequest {

    @NotBlank(message = "Full name is required")
    private final String fullName;

    /**
     * Constructs a new FullNameMatchKeyRequest with the specified API key and full name.
     *
     * @param apikey   The API key required for making the request.
     * @param fullName The full name for which the match key is generated.
     */
    public FullNameMatchKeyRequest(String apikey, String fullName) {
        super(apikey);
        this.fullName = fullName;
    }

    /**
     * Gets the full name.
     * @return The full name.
     */
    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return "FullNameMatchKeyRequest{" +
                "fullName='" + fullName + '\'' +
                '}';
    }
}
