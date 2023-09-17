package com.interzoid.sdk.model;

import com.squareup.moshi.Json;

/**
 * Represents the response structure for Interzoid API responses related to matching keys.
 * This class extends the InterzoidResponse class and contains an additional field to store the similarity key (SimKey).
 */
public class MatchKeyResponse extends InterzoidResponse {
/**
     * Default constructor for the {@link MatchKeyResponse} class.
     */
    public MatchKeyResponse() {
    }

    /**
     * The similarity key (SimKey) provided by the Interzoid API for matching purposes.
     */
    @Json(name = "SimKey")
    private String simKey;

    /**
     * Gets the similarity key (SimKey) provided by the Interzoid API.
     *
     * @return The similarity key.
     */
    public String getSimKey() {
        return simKey;
    }

    /**
     * Sets the similarity key (SimKey).
     *
     * @param simKey The similarity key to set.
     */
    public void setSimKey(String simKey) {
        this.simKey = simKey;
    }

    /**
     * Returns a string representation of the MatchKeyResponse object.
     *
     * @return A string containing the similarity key (SimKey) and response code/credits (inherited from InterzoidResponse).
     */
    @Override
    public String toString() {
        return "MatchKeyResponse{" +
                "simKey='" + simKey + '\'' +
                "} " + super.toString();
    }
}
