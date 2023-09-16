package com.interzoid.sdk.model;

import jakarta.validation.constraints.NotBlank;

public class OrganizationMatchScoreRequest extends InterzoidRequest {
    @NotBlank(message = "Value 1 is required")
    private final String value1;
    @NotBlank(message = "Value 2 is required")
    private final String value2;

    private OrganizationMatchScoreRequest(String apikey, String value1, String value2) {
        super(apikey);
        this.value1 = value1;
        this.value2 = value2;
    }

    public static OrganizationMatchScoreRequest create(String apikey, String value1, String value2) {
        return new OrganizationMatchScoreRequest(apikey, value1, value2);
    }

    public String getValue1() {
        return value1;
    }

    public String getValue2() {
        return value2;
    }

    @Override
    public String toString() {
        return "MatchScoreRequest{" +
                "value1='" + value1 + '\'' +
                ", value2='" + value2 + '\'' +
                '}';
    }
}
