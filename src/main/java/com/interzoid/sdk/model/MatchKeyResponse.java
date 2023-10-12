package com.interzoid.sdk.model;

import com.squareup.moshi.Json;

/**
 * 
 * Represents the response structure for Interzoid API responses related to matching keys.
 * This class extends the InterzoidResponse class and contains an additional field to store the similarity key (SimKey).
 * @see InterzoidResponse
 */
public class MatchKeyResponse extends InterzoidResponse {
    /**
     * Default constructor for the {@link MatchKeyResponse} class.
     */
    public MatchKeyResponse() {
    }

    @Json(name = "SimKey")
    private String simKey;

    /**
     * Gets the similarity key provided by the Interzoid API for the matched data.
     * @return The similarity key.
     */
    public String getSimKey() {
        return simKey;
    }

    /**
     * Sets the similarity key.
     * @param simKey The similarity key to set.
     */
    public void setSimKey(String simKey) {
        this.simKey = simKey;
    }

    @Override
    public String toString() {
        return "MatchKeyResponse{" +
                "simKey='" + simKey + '\'' +
                "} " + super.toString();
    }
}
