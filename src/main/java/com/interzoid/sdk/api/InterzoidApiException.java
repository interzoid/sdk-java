package com.interzoid.sdk.api;

import java.io.IOException;

/**
 * Exception thrown when the API returns an error code.
 */
public class InterzoidApiException extends IOException {
    /**
     * Constructs a new InterzoidApiException with the specified message.
     * @param message The message to set.
     */
    public InterzoidApiException(String message) {
        super(message);
    }

    /**
     * Constructs a new InterzoidApiException with the specified message and cause.
     * @param message The message to set.
     * @param cause The cause to set.
     */
    public InterzoidApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
