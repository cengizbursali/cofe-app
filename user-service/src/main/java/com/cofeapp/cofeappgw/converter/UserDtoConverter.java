package com.cofeapp.cofeappgw.converter;

import com.cofeapp.cofeappgw.entity.User;
import com.cofeapp.cofeappgw.model.response.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public UserDto convert(User user) {
        return UserDto.builder()
                .id(user.getId())
                .country(user.getCountry())
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .build();
    }
}
