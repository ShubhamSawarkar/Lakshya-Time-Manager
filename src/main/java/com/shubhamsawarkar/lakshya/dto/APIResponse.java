package com.shubhamsawarkar.lakshya.dto;

import com.shubhamsawarkar.lakshya.constants.APIResponseStatus;

public record APIResponse<T>(APIResponseStatus status, String message, T body) {

    public APIResponse(APIResponseStatus status, String message) {
        this(status, message, null);
    }
}
