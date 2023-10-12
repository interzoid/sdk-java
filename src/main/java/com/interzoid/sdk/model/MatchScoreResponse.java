package com.interzoid.sdk.model;

import com.squareup.moshi.Json;

/**
 * 
 * Represents the response structure for Interzoid API responses related to matching scores.
 * This class extends the InterzoidResponse class and contains an additional field to store the matching score (Score).
 * @see InterzoidResponse
 */
public class MatchScoreResponse extends InterzoidResponse {
    /**
     * Default constructor for the {@link MatchScoreResponse} class.
     */
    public MatchScoreResponse() {
    }

    @Json(name = "Score")
    private String score;

    /**
     * Gets the matching score provided by the Interzoid API for the matched data.
     * @return TThe matching score provided by the Interzoid API for the matched data.
     */
    public String getScore() {
        return score;
    }

    /**
     * Sets the matching score provided by the Interzoid API for the matched data.
     * @param score The matching score to set.
     */
    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "MatchScoreResponse{" +
                "score='" + score + '\'' +
                "} " + super.toString();
    }
}
