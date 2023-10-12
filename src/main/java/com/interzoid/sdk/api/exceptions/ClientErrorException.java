package com.interzoid.sdk.api.exceptions;

/**
 * 
 * Exception thrown when the API returns a 4xx error code, indicating client-side errors.
 * This class extends {@link InterzoidApiException} to provide more specific error handling
 * for client errors encountered when interacting with the API.
 * @version 1.0
 */
public class ClientErrorException extends InterzoidApiException {
    /**
     * Constructs a new {@code ClientErrorException} with the specified error message.
     * The message is used to provide additional information about the error which can
     * be useful for debugging or logging purposes.
     *
     * @param message the detailed error message explaining the client error
     */
    public ClientErrorException(String message) {
        super(message);
    }
}
