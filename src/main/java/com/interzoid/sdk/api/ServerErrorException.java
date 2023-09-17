package com.interzoid.sdk.api;

/**
 * Exception thrown when the API returns a 5xx error code.
 */
public class ServerErrorException extends InterzoidApiException {
    /**
     * Constructs a new ServerErrorException with the specified message and cause.
     * @param message   The message to set.
     */
    public ServerErrorException(String message) {
        super(message);
    }
}
