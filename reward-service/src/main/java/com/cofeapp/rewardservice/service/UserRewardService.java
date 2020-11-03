package com.cofeapp.rewardservice.service;

import com.cofeapp.rewardservice.entity.UserReward;
import com.cofeapp.rewardservice.exception.RewardServiceException;
import com.cofeapp.rewardservice.model.request.UserRewardAssignRequest;
import com.cofeapp.rewardservice.model.response.RewardDto;
import com.cofeapp.rewardservice.repository.UserRewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRewardService {

    private static final String REWARD_HAS_EXPIRED = "reward.has.expired";

    private final RewardService rewardService;
    private final UserRewardRepository userRewardRepository;

    public void assign(UserRewardAssignRequest userRewardAssignRequest) {
        final RewardDto rewardDto = rewardService.getById(userRewardAssignRequest.getRewardId());
        if (rewardDto.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RewardServiceException(REWARD_HAS_EXPIRED, HttpStatus.BAD_REQUEST);
        }
        final UserReward userReward = UserReward.builder()
                .userId(userRewardAssignRequest.getUserId())
                .rewardId(userRewardAssignRequest.getRewardId())
                .build();
        userRewardRepository.save(userReward);
    }

    public List<Integer> getUserIdListByRewardId(Integer rewardId) {
        return userRewardRepository.findAllByRewardId(rewardId).stream().map(UserReward::getUserId).collect(Collectors.toList());
    }
}
