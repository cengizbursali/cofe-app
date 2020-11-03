package com.cofeapp.cofeappgw.client;

import com.cofeapp.cofeappgw.model.Reward;
import com.cofeapp.cofeappgw.model.request.RewardCreateRequest;
import com.cofeapp.cofeappgw.model.request.UserRewardAssignRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "rewardServiceClient", url = "${reward.service.url}")
public interface RewardServiceClient {

    @PostMapping(value = "/rewards")
    void create(@RequestBody RewardCreateRequest rewardCreateRequest);

    @GetMapping(value = "/rewards/{id}")
    Reward get(@PathVariable Integer id);

    @GetMapping(value = "/rewards")
    List<Reward> getAll();

    @PostMapping("/user-rewards")
    void assignUser(@RequestBody UserRewardAssignRequest userRewardAssignRequest);

    @GetMapping("/user-rewards")
    List<Integer> getUserIdListByRewardId(@RequestParam("rewardId") Integer rewardId);

    @GetMapping(value = "/rewards")
    List<Reward> getAllByUserId(@RequestParam("userId") Integer userId);
}
