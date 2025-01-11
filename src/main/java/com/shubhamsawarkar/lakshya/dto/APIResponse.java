package com.shubhamsawarkar.lakshya.dto;

import com.shubhamsawarkar.lakshya.constant.APIResponseStatus;

public record APIResponse(APIResponseStatus status, String message, ResponseBody body) {

    public APIResponse(APIResponseStatus status, String message) {
        this(status, message, null);
    }
}
