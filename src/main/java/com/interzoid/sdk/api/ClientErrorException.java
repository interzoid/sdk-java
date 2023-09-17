package com.interzoid.sdk.api;

/**
 * Exception thrown when the API returns a 4xx error code.
 */
public class ClientErrorException extends InterzoidApiException {
    /**
     * Constructs a new ClientErrorException with the specified message.
     *
     * @param message The message to set.
     */
    public ClientErrorException(String message) {
        super(message);
    }
}
