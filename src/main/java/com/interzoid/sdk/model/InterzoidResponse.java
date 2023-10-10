package com.interzoid.sdk.model;

import com.squareup.moshi.Json;

/**
 * @author Interzoid
 * @version 1.1
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
     * @return The response code.
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code The response code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return The available credits.
     */
    public String getCredits() {
        return credits;
    }

    /**
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
