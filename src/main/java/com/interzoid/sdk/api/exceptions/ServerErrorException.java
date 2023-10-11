package com.interzoid.sdk.api.exceptions;

/**
 * 
 * Exception thrown when the API returns a 5xx error code, indicating server-side errors.
 * This class extends {@link InterzoidApiException} to provide more specific error handling
 * for server errors encountered when interacting with the API.
 */
public class ServerErrorException extends InterzoidApiException {
    /**
     * Constructs a new ServerErrorException with the specified message and cause.
     *
     * @param message The message to set.
     */
    public ServerErrorException(String message) {
        super(message);
    }
}
