package com.interzoid.sdk.model;

import com.squareup.moshi.Json;

/**
 * @author Interzoid
 * @version 1.1
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

    /**
     * The matching score provided by the Interzoid API for the matched data.
     */
    @Json(name = "Score")
    private String score;

    /**
     * Gets the matching score provided by the Interzoid API.
     *
     * @return The matching score.
     */
    public String getScore() {
        return score;
    }

    /**
     * @param score The matching score to set.
     */
    public void setScore(String score) {
        this.score = score;
    }

    /**
     * Returns a string representation of the MatchScoreResponse object.
     *
     * @return A string containing the matching score (Score) and response code/credits (inherited from InterzoidResponse).
     */
    @Override
    public String toString() {
        return "MatchScoreResponse{" +
                "score='" + score + '\'' +
                "} " + super.toString();
    }
}
