package com.cofeapp.rewardservice.converter;

import com.cofeapp.rewardservice.entity.Reward;
import com.cofeapp.rewardservice.model.request.RewardCreateRequest;
import org.springframework.stereotype.Component;

@Component
public class RewardConverter {
    public Reward convert(RewardCreateRequest rewardCreateRequest) {
        return Reward.builder()
                .name(rewardCreateRequest.getName())
                .amount(rewardCreateRequest.getAmount())
                .expiryDate(rewardCreateRequest.getExpiryDate())
                .build();
    }
}
