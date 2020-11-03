package com.cofeapp.cofeappgw.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class UserServiceException extends RuntimeException {

    private final String key;
    private final HttpStatus httpStatus;

    public UserServiceException(String key, HttpStatus httpStatus) {
        this.key = key;
        this.httpStatus = httpStatus;
    }
}
