package com.shubhamsawarkar.lakshya.constants;

public enum APIResponseStatus {

    OK("Success", 200),
    INVALID_REQUEST("Invalid Request", 400),
    NOT_FOUND("Not Found", 404),
    INTERNAL_SERVER_ERROR("Internal Server Error", 500);

    private final String status;

    private final int code;

    APIResponseStatus(String status, int code) {
        this.status = status;
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return status;
    }
}
