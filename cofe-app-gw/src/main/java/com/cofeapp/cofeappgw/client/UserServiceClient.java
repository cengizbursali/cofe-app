package com.cofeapp.cofeappgw.client;

import com.cofeapp.cofeappgw.model.User;
import com.cofeapp.cofeappgw.model.request.UserCreateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "userServiceClient", url = "${user.service.url}")
public interface UserServiceClient {

    @PostMapping(value = "/users")
    void create(@RequestBody UserCreateRequest unitSaveRequest);

    @GetMapping(value = "/users/{id}")
    User get(@PathVariable Integer id);

    @GetMapping(value = "/users/")
    List<User> getAll();

    @GetMapping(value = "/users/")
    List<User> getAllByUserIdList(@RequestParam("userIdList") List<Integer> userIdList);
}
