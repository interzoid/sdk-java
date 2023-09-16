package com.interzoid.sdk.api;

public class UnexpectedResponseException extends RuntimeException {
    public UnexpectedResponseException(String message) {
        super(message);
    }
}
