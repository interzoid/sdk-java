package com.interzoid.sdk.model;

import jakarta.validation.constraints.NotBlank;

public class FullNameMatchKeyRequest extends InterzoidRequest {
    @NotBlank(message = "Full name is required")
    private final String fullName;

    private FullNameMatchKeyRequest(String apikey, String fullName) {
        super(apikey);
        this.fullName = fullName;
    }

    public static FullNameMatchKeyRequest create(String apikey, String fullname) {
        return new FullNameMatchKeyRequest(apikey, fullname);
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public String toString() {
        return "FullNameMatchKeyRequest{" +
                "fullname='" + fullName + '\'' +
                '}';
    }
}
