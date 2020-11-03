package com.cofeapp.cofeappgw.converter;

import com.cofeapp.cofeappgw.entity.User;
import com.cofeapp.cofeappgw.model.request.UserCreateRequest;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User convert(UserCreateRequest userCreateRequest) {
        return User.builder()
                .country(userCreateRequest.getCountry())
                .email(userCreateRequest.getEmail())
                .name(userCreateRequest.getName())
                .phone(userCreateRequest.getPhone())
                .build();
    }
}
