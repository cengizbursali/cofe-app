package com.cofeapp.cofeappgw.converter;

import com.cofeapp.cofeappgw.model.Reward;
import com.cofeapp.cofeappgw.model.User;
import com.cofeapp.cofeappgw.model.response.RewardGwResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RewardGwResponseConverter {

    public RewardGwResponse convert(Reward reward, List<User> userList) {
        return RewardGwResponse.builder()
                .id(reward.getId())
                .amount(reward.getAmount())
                .expiryDate(reward.getExpiryDate())
                .name(reward.getName())
                .userList(userList)
                .build();
    }
}
