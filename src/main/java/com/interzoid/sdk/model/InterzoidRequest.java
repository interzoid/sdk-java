package com.interzoid.sdk.model;

import jakarta.validation.constraints.NotBlank;

public class InterzoidRequest {
    @NotBlank(message = "API key is required")
    private final String apikey;

    public InterzoidRequest(String apikey) {
        this.apikey = apikey;
    }

    public String getApikey() {
        return apikey;
    }
}
