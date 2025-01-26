package com.shubhamsawarkar.lakshya.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.shubhamsawarkar.lakshya.constant.APIResponseStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record APIResponse(APIResponseStatus status, String message, ResponseBody data) {

    public APIResponse(APIResponseStatus status, String message) {
        this(status, message, null);
    }
}
