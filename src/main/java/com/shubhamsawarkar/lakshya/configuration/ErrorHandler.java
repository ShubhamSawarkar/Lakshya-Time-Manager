package com.shubhamsawarkar.lakshya.configuration;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.shubhamsawarkar.lakshya.constant.APIResponseStatus;
import com.shubhamsawarkar.lakshya.dto.APIResponse;
import com.shubhamsawarkar.lakshya.exception.InvalidRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.DateTimeException;
import java.util.Objects;

@ControllerAdvice
public class ErrorHandler {

    private static final String GENERIC_ERROR_MSG = "Invalid Request - Please verify the request again";

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<APIResponse> handleInvalidRequestException(InvalidRequestException exception) {
        APIResponse response =  new APIResponse(APIResponseStatus.INVALID_REQUEST
                                              , exception.getMessage()
                                              , exception.getBody());

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String errorMessage = Objects.nonNull(exception.getBindingResult().getFieldError())
                             ? exception.getBindingResult().getFieldError().getDefaultMessage()
                             : GENERIC_ERROR_MSG;

        APIResponse response = new APIResponse(APIResponseStatus.INVALID_REQUEST
                                             , errorMessage);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DateTimeException.class)
    public ResponseEntity<APIResponse> handleDateTimeParseException(DateTimeException exception) {
        String errorMessage = "Invalid date/time format - "
                             + exception.getMessage()
                             + ". All the dates must be in the format 'yyyy-MM-dd' and all the times must be in the format 'HH:mm'. ";

        APIResponse response = new APIResponse(APIResponseStatus.INVALID_REQUEST
                                             , errorMessage);

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<APIResponse> handleInvalidDataTypeException(Exception exception) {
        String fieldName = null, currentValue = null, targetDataType = null;

        if (exception instanceof HttpMessageNotReadableException && exception.getCause() instanceof InvalidFormatException cause) {
            JsonMappingException.Reference ref = cause.getPath().stream().findFirst().orElse(null);
            fieldName = Objects.nonNull(ref) ? ref.getFieldName() : null;
            currentValue = Objects.nonNull(cause.getValue()) ? cause.getValue().toString() : null;
            targetDataType = Objects.nonNull(cause.getTargetType()) ? cause.getTargetType().getSimpleName() : null;
        } else if (exception instanceof MethodArgumentTypeMismatchException typeMismatchException) {
            fieldName = typeMismatchException.getName();
            currentValue = Objects.nonNull(typeMismatchException.getValue()) ? typeMismatchException.getValue().toString() : null;
            targetDataType = Objects.nonNull(typeMismatchException.getRequiredType()) ? typeMismatchException.getRequiredType().getSimpleName() : null;
        }

        String errorMessage = GENERIC_ERROR_MSG;
        if (Objects.nonNull(fieldName) && Objects.nonNull(targetDataType)) {
            errorMessage = "Invalid value '"
                         + currentValue
                         + "' for the field '"
                         + fieldName
                         + "', expected a valid value of type '"
                         + targetDataType
                         + "'";
        }

        APIResponse response = new APIResponse(APIResponseStatus.INVALID_REQUEST
                                             , errorMessage);

        return ResponseEntity.badRequest().body(response);
    }
}
