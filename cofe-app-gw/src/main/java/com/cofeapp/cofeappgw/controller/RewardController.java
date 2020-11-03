package com.cofeapp.cofeappgw.controller;

import com.cofeapp.cofeappgw.model.Reward;
import com.cofeapp.cofeappgw.model.request.RewardCreateRequest;
import com.cofeapp.cofeappgw.model.request.UserRewardAssignRequest;
import com.cofeapp.cofeappgw.model.response.RewardGwResponse;
import com.cofeapp.cofeappgw.service.RewardService;
import com.cofeapp.cofeappgw.service.UserRewardService;
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
@RequestMapping
public class RewardController {

    private final RewardService rewardService;
    private final UserRewardService userRewardService;

    @PostMapping("/rewards")
    @ResponseStatus(CREATED)
    public void create(@RequestBody RewardCreateRequest rewardCreateRequest) {
        log.info("rewardCreateRequest: {}", rewardCreateRequest);
        rewardService.create(rewardCreateRequest);
    }

    @GetMapping("/rewards")
    public List<Reward> getAll() {
        return rewardService.getAll();
    }

    @GetMapping("/rewards/{id}")
    public RewardGwResponse getById(@PathVariable Integer id) {
        return userRewardService.getReward(id);
    }

    @PostMapping("/user-rewards")
    @ResponseStatus(CREATED)
    public void assign(@RequestBody UserRewardAssignRequest userRewardAssignRequest) {
        log.info("userRewardAssignRequest: {}", userRewardAssignRequest);
        userRewardService.assignUser(userRewardAssignRequest);
    }
}
