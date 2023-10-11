package com.interzoid.sdk.model;

import com.squareup.moshi.Json;

/**
 * Represents the response structure for Interzoid API responses.
 * This class contains fields to store the API response code and available credits.
 */
public class InterzoidResponse {
    /**
     * Default constructor for the {@link InterzoidResponse} class.
     */
    public InterzoidResponse() {
    }

    @Json(name = "Code")
    private String code;

    @Json(name = "Credits")
    private String credits;

    /**
     * Gets the response code.
     * @return The response code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the response code.
     * @param code The response code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the available credits.
     * @return The available credits.
     */
    public String getCredits() {
        return credits;
    }

    /**
     * Sets the available credits.
     * @param credits The available credits to set.
     */
    public void setCredits(String credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "InterzoidResponse{" +
                "code='" + code + '\'' +
                ", credits='" + credits + '\'' +
                '}';
    }
}
