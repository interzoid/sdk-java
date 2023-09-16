package com.interzoid.sdk.model;

import com.squareup.moshi.Json;

public class MatchKeyResponse extends InterzoidResponse {
    @Json(name = "SimKey")
    private String simKey;

    public String getSimKey() {
        return simKey;
    }

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
