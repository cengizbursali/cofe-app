package com.cofeapp.cofeappgw.service;

import com.cofeapp.cofeappgw.client.UserServiceClient;
import com.cofeapp.cofeappgw.model.User;
import com.cofeapp.cofeappgw.model.request.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserServiceClient userServiceClient;

    public void create(UserCreateRequest userCreateRequest) {
        userServiceClient.create(userCreateRequest);
    }

    public User getById(Integer id) {
        return userServiceClient.get(id);
    }

    public List<User> getAll() {
        return userServiceClient.getAll();
    }

    public List<User> getAllByUserIdList(List<Integer> userIdList) {
        return userServiceClient.getAllByUserIdList(userIdList);
    }
}
