package com.interzoid.sdk.model;

import com.interzoid.sdk.api.FullNameMatchScoreApi;
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

    /**
     * The response code returned by the Interzoid API.
     */
    @Json(name = "Code")
    private String code;

    /**
     * The number of credits available after making the API request.
     */
    @Json(name = "Credits")
    private String credits;

    /**
     * Gets the response code returned by the Interzoid API.
     *
     * @return The response code.
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the response code.
     *
     * @param code The response code to set.
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the number of credits available after making the API request.
     *
     * @return The available credits.
     */
    public String getCredits() {
        return credits;
    }

    /**
     * Sets the available credits.
     *
     * @param credits The available credits to set.
     */
    public void setCredits(String credits) {
        this.credits = credits;
    }

    /**
     * Returns a string representation of the InterzoidResponse object.
     *
     * @return A string containing the response code and available credits.
     */
    @Override
    public String toString() {
        return "InterzoidResponse{" +
                "code='" + code + '\'' +
                ", credits='" + credits + '\'' +
                '}';
    }
}
