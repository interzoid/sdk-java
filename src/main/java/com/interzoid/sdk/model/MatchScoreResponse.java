package com.interzoid.sdk.model;

import com.squareup.moshi.Json;

public class MatchScoreResponse extends InterzoidResponse {
    @Json(name = "Score")
    private String score;

    public String getScore() {
        return score;
    }

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
