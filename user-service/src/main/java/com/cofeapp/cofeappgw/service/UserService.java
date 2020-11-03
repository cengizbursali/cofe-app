package com.cofeapp.cofeappgw.service;

import com.cofeapp.cofeappgw.converter.UserConverter;
import com.cofeapp.cofeappgw.converter.UserDtoConverter;
import com.cofeapp.cofeappgw.entity.User;
import com.cofeapp.cofeappgw.exception.UserServiceException;
import com.cofeapp.cofeappgw.model.request.UserCreateRequest;
import com.cofeapp.cofeappgw.model.response.UserDto;
import com.cofeapp.cofeappgw.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final String USER_NOT_FOUND = "user.not.found";

    private final UserConverter userConverter;
    private final UserDtoConverter userDtoConverter;
    private final UserRepository userRepository;

    public void create(UserCreateRequest userCreateRequest) {
        final User user = userConverter.convert(userCreateRequest);
        userRepository.save(user);
    }

    public List<UserDto> getAllByFilter(List<Integer> userIdList) {
        List<User> users;
        if (CollectionUtils.isEmpty(userIdList)) {
            users = userRepository.findAll();
        } else {
            users = userRepository.findAllByIdIn(userIdList);
        }
        return users.stream().map(userDtoConverter::convert).collect(Collectors.toList());
    }

    public UserDto getById(Integer id) {
        return userRepository.findById(id)
                .map(userDtoConverter::convert)
                .orElseThrow(() -> new UserServiceException(USER_NOT_FOUND, HttpStatus.NOT_FOUND));
    }
}
