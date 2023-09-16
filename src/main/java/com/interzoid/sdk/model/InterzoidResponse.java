package com.interzoid.sdk.model;

import com.squareup.moshi.Json;

public class InterzoidResponse {
    @Json(name = "Code")
    private String code;
    @Json(name = "Credits")
    private String credits;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCredits() {
        return credits;
    }

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
