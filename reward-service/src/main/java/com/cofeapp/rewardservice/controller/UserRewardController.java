package com.cofeapp.rewardservice.controller;

import com.cofeapp.rewardservice.model.request.UserRewardAssignRequest;
import com.cofeapp.rewardservice.service.UserRewardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/user-rewards")
public class UserRewardController {

    private final UserRewardService userRewardService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void assignUser(@RequestBody @Valid UserRewardAssignRequest userRewardAssignRequest) {
        log.info("userRewardAssignRequest: {}", userRewardAssignRequest);
        userRewardService.assign(userRewardAssignRequest);
    }

    @GetMapping
    public List<Integer> getUserIdListByRewardId(@RequestParam("rewardId") Integer rewardId) {
        return userRewardService.getUserIdListByRewardId(rewardId);
    }
}
