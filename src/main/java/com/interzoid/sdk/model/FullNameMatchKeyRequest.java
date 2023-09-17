package com.interzoid.sdk.model;

import jakarta.validation.constraints.NotBlank;

/**
 * Represents a request for generating a full name match key, used for comparing and sorting full names.
 * This class is used to specify the full name and API key required for the request.
 */
public class FullNameMatchKeyRequest extends InterzoidRequest {

    /**
     * The full name for which the match key is generated.
     */
    @NotBlank(message = "Full name is required")
    private final String fullName;

    /**
     * Constructs a new FullNameMatchKeyRequest with the specified API key and full name.
     *
     * @param apikey    The API key required for making the request.
     * @param fullName  The full name for which the match key is generated.
     */
    public FullNameMatchKeyRequest(String apikey, String fullName) {
        super(apikey);
        this.fullName = fullName;
    }

    /**
     * Gets the full name for which the match key is generated.
     *
     * @return The full name.
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Generates a string representation of the FullNameMatchKeyRequest for debugging purposes.
     *
     * @return A string containing the full name.
     */
    @Override
    public String toString() {
        return "FullNameMatchKeyRequest{" +
                "fullName='" + fullName + '\'' +
                '}';
    }
}
