package com.cofeapp.rewardservice.exception;

import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class RewardServiceException extends RuntimeException {

    private final String key;
    private final HttpStatus httpStatus;

    public RewardServiceException(String key, HttpStatus httpStatus) {
        this.key = key;
        this.httpStatus = httpStatus;
    }
}
