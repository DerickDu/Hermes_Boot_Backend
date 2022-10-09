package com.derick.hermes.exception;

public class InvalidSearchDateException extends RuntimeException {
    public InvalidSearchDateException(String message) {
        super(message);
    }
}