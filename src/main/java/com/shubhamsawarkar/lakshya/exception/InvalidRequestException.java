package com.shubhamsawarkar.lakshya.exception;

import com.shubhamsawarkar.lakshya.dto.ResponseBody;

public class InvalidRequestException extends RuntimeException {

    private final ResponseBody body;

    public InvalidRequestException(String message, ResponseBody body) {
        super(message);
        this.body = body;
    }

    public InvalidRequestException(String message) {
        this(message, null);
    }

    public ResponseBody getBody() {
        return body;
    }
}
