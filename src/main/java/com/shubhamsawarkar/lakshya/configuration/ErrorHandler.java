package com.shubhamsawarkar.lakshya.configuration;

import com.shubhamsawarkar.lakshya.constant.APIResponseStatus;
import com.shubhamsawarkar.lakshya.dto.APIResponse;
import com.shubhamsawarkar.lakshya.exception.InvalidRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.DateTimeException;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<APIResponse> handleInvalidRequestException(InvalidRequestException exception) {
        APIResponse response =  new APIResponse(APIResponseStatus.INVALID_REQUEST
                                              , exception.getMessage()
                                              , exception.getBody());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        APIResponse response = new APIResponse(APIResponseStatus.INVALID_REQUEST
                                             , exception.getMessage());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DateTimeException.class)
    public ResponseEntity<APIResponse> handleDateTimeParseException(DateTimeException exception) {
        APIResponse response = new APIResponse(APIResponseStatus.INVALID_REQUEST
                                             , """
                                               Invalid date/time format - " + exception.getMessage() + ".
                                               All the dates must be in the format 'yyyy-MM-dd' and all the times must be in the format 'HH:mm'.
                                               """);

        return ResponseEntity.badRequest().body(response);
    }
}
