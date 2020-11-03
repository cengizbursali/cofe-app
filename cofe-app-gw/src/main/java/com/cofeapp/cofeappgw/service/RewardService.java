package com.cofeapp.cofeappgw.service;

import com.cofeapp.cofeappgw.client.RewardServiceClient;
import com.cofeapp.cofeappgw.model.Reward;
import com.cofeapp.cofeappgw.model.request.RewardCreateRequest;
import com.cofeapp.cofeappgw.model.request.UserRewardAssignRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RewardService {

    private final RewardServiceClient rewardServiceClient;

    public void create(RewardCreateRequest rewardCreateRequest) {
        rewardServiceClient.create(rewardCreateRequest);
    }

    public Reward getById(Integer id) {
        return rewardServiceClient.get(id);
    }

    public List<Reward> getAllByUserId(Integer userId) {
        return rewardServiceClient.getAllByUserId(userId);
    }

    public List<Reward> getAll() {
        return rewardServiceClient.getAll();
    }

    public List<Integer> getUserIdListByRewardId(Integer rewardId) {
        return rewardServiceClient.getUserIdListByRewardId(rewardId);
    }

    public void assignUser(UserRewardAssignRequest userRewardAssignRequest) {
        rewardServiceClient.assignUser(userRewardAssignRequest);
    }
}
