package com.cofeapp.rewardservice.converter;

import com.cofeapp.rewardservice.entity.Reward;
import com.cofeapp.rewardservice.model.response.RewardDto;
import org.springframework.stereotype.Component;

@Component
public class RewardDtoConverter {

    public RewardDto convert(Reward reward) {
        return RewardDto.builder()
                .id(reward.getId())
                .amount(reward.getAmount())
                .expiryDate(reward.getExpiryDate())
                .name(reward.getName())
                .build();
    }
}
