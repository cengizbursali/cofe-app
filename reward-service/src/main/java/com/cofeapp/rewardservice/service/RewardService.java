package com.cofeapp.rewardservice.service;

import com.cofeapp.rewardservice.converter.RewardConverter;
import com.cofeapp.rewardservice.converter.RewardDtoConverter;
import com.cofeapp.rewardservice.entity.Reward;
import com.cofeapp.rewardservice.entity.UserReward;
import com.cofeapp.rewardservice.exception.RewardServiceException;
import com.cofeapp.rewardservice.model.request.RewardCreateRequest;
import com.cofeapp.rewardservice.model.response.RewardDto;
import com.cofeapp.rewardservice.repository.RewardRepository;
import com.cofeapp.rewardservice.repository.UserRewardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RewardService {

    private static final String REWARD_NOT_FOUND = "reward.not.found";

    private final RewardConverter rewardConverter;
    private final RewardDtoConverter rewardDtoConverter;
    private final RewardRepository rewardRepository;
    private final UserRewardRepository userRewardRepository;

    public void create(RewardCreateRequest rewardCreateRequest) {
        final Reward reward = rewardConverter.convert(rewardCreateRequest);
        rewardRepository.save(reward);
    }

    public List<RewardDto> getAllByFilter(Integer userId) {
        List<Reward> rewardList;
        if (Objects.isNull(userId)) {
            rewardList = rewardRepository.findAll();
        } else {
            final List<Integer> rewardIdList = userRewardRepository.findAllByUserId(userId)
                    .stream()
                    .map(UserReward::getRewardId)
                    .collect(Collectors.toList());
            rewardList = rewardRepository.findAllByIdIn(rewardIdList);
        }
        return rewardList.stream().map(rewardDtoConverter::convert).collect(Collectors.toList());
    }

    public RewardDto getById(Integer id) {
        return rewardRepository.findById(id)
                .map(rewardDtoConverter::convert)
                .orElseThrow(() -> new RewardServiceException(REWARD_NOT_FOUND, HttpStatus.NOT_FOUND));
    }

}
