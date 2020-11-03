package com.cofeapp.cofeappgw.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class CofeAppGwException extends RuntimeException {

    private final String key;
    private final HttpStatus httpStatus;

    public CofeAppGwException(String key, HttpStatus httpStatus) {
        this.key = key;
        this.httpStatus = httpStatus;
    }
}
