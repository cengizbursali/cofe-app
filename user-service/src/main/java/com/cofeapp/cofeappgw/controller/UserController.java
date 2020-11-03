package com.cofeapp.cofeappgw.controller;

import com.cofeapp.cofeappgw.model.request.UserCreateRequest;
import com.cofeapp.cofeappgw.model.request.UserFilterRequest;
import com.cofeapp.cofeappgw.model.response.UserDto;
import com.cofeapp.cofeappgw.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void create(@RequestBody @Valid UserCreateRequest userCreateRequest) {
        log.info("userCreateRequest: {}", userCreateRequest);
        userService.create(userCreateRequest);
    }

    @GetMapping
    public List<UserDto> getAllByFilter(UserFilterRequest userFilterRequest) {
        return userService.getAllByFilter(userFilterRequest.getUserIdList());
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Integer id) {
        return userService.getById(id);
    }
}
