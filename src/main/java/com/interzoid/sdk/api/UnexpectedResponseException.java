package com.interzoid.sdk.api;

/**
 * Exception thrown when the API returns an unexpected response.
 */
public class UnexpectedResponseException extends InterzoidApiException {
    /**
     * Constructs a new UnexpectedResponseException with the specified message.
     * @param message The message to set.
     */
    public UnexpectedResponseException(String message) {
        super(message);
    }

    /**
     * Constructs a new UnexpectedResponseException with the specified message and cause.
     * @param message The message to set.
     * @param cause The cause to set.
     */
    public UnexpectedResponseException(String message, Throwable cause) {
        super(message, cause);
    }
}
