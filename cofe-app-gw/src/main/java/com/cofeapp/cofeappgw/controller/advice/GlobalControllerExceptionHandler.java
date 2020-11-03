package com.cofeapp.cofeappgw.controller.advice;

import com.cofeapp.cofeappgw.exception.CofeAppGwException;
import com.cofeapp.cofeappgw.model.error.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerExceptionHandler {

    private static final Locale EN = new Locale("en");
    private final MessageSource messageSource;
    private final ObjectMapper objectMapper;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.error("Generic exception occurred.", exception);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .exception(exception.getClass().getName())
                .timestamp(System.currentTimeMillis())
                .errors(Collections.singletonList("Generic exception occurred."))
                .build();
        return new ResponseEntity<>(errorResponse, INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CofeAppGwException.class)
    public ResponseEntity<ErrorResponse> handleCofeAppException(CofeAppGwException exception) {
        final String defaultMessage = "CofeAppGwException occurred.";
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .exception("CofeAppGwException")
                .error(messageSource.getMessage(exception.getKey(), new String[]{}, defaultMessage, EN))
                .timestamp(System.currentTimeMillis())
                .build();
        log.error(defaultMessage, exception);
        return new ResponseEntity<>(errorResponse, exception.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        log.error("MethodArgumentNotValidException occurred.", exception);
        final List<String> errorMessages = exception.getBindingResult().getFieldErrors().stream()
                .map(this::getMessage)
                .collect(Collectors.toList());
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .exception("MethodArgumentNotValidException")
                .errors(errorMessages)
                .timestamp(System.currentTimeMillis())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(BindException exception) {
        log.error("BindException occurred.", exception);
        final List<String> errorMessages = exception.getBindingResult().getFieldErrors().stream()
                .map(this::getMessage)
                .collect(Collectors.toList());
        final ErrorResponse errorResponse = ErrorResponse.builder()
                .exception("BindException")
                .errors(errorMessages)
                .timestamp(System.currentTimeMillis())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> handle(FeignException exception) {
        log.error("Api client exception occurred. Detail: " + exception.contentUTF8(), exception);
        return new ResponseEntity<>(resolve(exception), HttpStatus.valueOf(exception.status()));
    }

    private ErrorResponse resolve(FeignException exception) {
        try {
            return objectMapper.readValue(exception.contentUTF8(), ErrorResponse.class);
        } catch (IOException e) {
            return ErrorResponse.builder()
                    .exception("ApiClientException")
                    .errors(Collections.singletonList("Api client exception occurred. Detail: " + exception.contentUTF8()))
                    .timestamp(System.currentTimeMillis())
                    .build();
        }
    }

    private String getMessage(FieldError error) {
        final String messageKey = error.getDefaultMessage();
        return messageSource.getMessage(messageKey, error.getArguments(), messageKey, EN);
    }
}
