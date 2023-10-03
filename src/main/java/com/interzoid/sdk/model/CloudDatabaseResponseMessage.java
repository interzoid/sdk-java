package com.interzoid.sdk.model;

public class CloudDatabaseResponseMessage implements CloudConnectResponse {
    private String message;

    public CloudDatabaseResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
