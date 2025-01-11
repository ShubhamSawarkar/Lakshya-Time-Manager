package com.shubhamsawarkar.lakshya.configuration;

import com.shubhamsawarkar.lakshya.constant.APIResponseStatus;
import com.shubhamsawarkar.lakshya.dto.APIResponse;
import com.shubhamsawarkar.lakshya.exception.InvalidRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<APIResponse> handleInvalidRequestException(InvalidRequestException exception) {
        APIResponse response =  new APIResponse(APIResponseStatus.INVALID_REQUEST
                                              , exception.getMessage()
                                              , exception.getBody());

        return ResponseEntity.badRequest().body(response);
    }
}
