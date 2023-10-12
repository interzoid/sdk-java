package com.interzoid.sdk.api.exceptions;

/**
 * Exception thrown when the API returns an unexpected response.
 * This class extends {@link InterzoidApiException} to provide more specific error handling
 * for unexpected responses encountered when interacting with the API.
 */
public class UnexpectedResponseException extends InterzoidApiException {
    /**
     * Constructs a new UnexpectedResponseException with the specified message.
     *
     * @param message The message to set.
     */
    public UnexpectedResponseException(String message) {
        super(message);
    }

    /**
     * Constructs a new UnexpectedResponseException with the specified message and cause.
     *
     * @param message The message to set.
     * @param cause   The cause to set.
     */
    public UnexpectedResponseException(String message, Throwable cause) {
        super(message, cause);
    }
}
