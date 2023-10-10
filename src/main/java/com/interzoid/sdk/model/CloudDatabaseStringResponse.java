package com.interzoid.sdk.model;

/**
 * @author Interzoid
 * @version 1.1
 *
 * This is a string response message for the Cloud Database service
 * @see CloudConnectResponse
 */
public class CloudDatabaseStringResponse implements CloudConnectResponse {
    private final String message;

    /**
     * Constructs a new CloudDatabaseStringResponse with the specified message.
     * @param message The response message.
     */
    public CloudDatabaseStringResponse(String message) {
        this.message = message;
    }

    /**
     * @return The response message.
     */
    public String getMessage() {
        return message;
    }
}
