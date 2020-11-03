package com.cofeapp.cofeappgw.controller;

import com.cofeapp.cofeappgw.model.User;
import com.cofeapp.cofeappgw.model.request.UserCreateRequest;
import com.cofeapp.cofeappgw.model.response.UserGwResponse;
import com.cofeapp.cofeappgw.service.UserRewardService;
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

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserRewardService userRewardService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void create(@RequestBody UserCreateRequest userCreateRequest) {
        log.info("userCreateRequest: {}", userCreateRequest);
        userService.create(userCreateRequest);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserGwResponse getById(@PathVariable Integer id) {
        return userRewardService.getUser(id);
    }
}
