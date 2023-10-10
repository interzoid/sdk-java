package com.interzoid.sdk.model;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Interzoid
 * @version 1.1
 * Represents a base request class for Interzoid API requests.
 * This class contains the common attribute 'apikey' required for authenticating API requests.
 */
public class InterzoidRequest {

    @NotBlank(message = "API key is required")
    private final String apikey;

    /**
     * Constructs a new InterzoidRequest with the specified API key.
     *
     * @param apikey The API key required for authenticating API requests.
     */
    public InterzoidRequest(String apikey) {
        this.apikey = apikey;
    }

    /**
     * @return The API key.
     */
    public String getApikey() {
        return apikey;
    }
}
