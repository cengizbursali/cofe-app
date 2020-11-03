package com.cofeapp.cofeappgw.converter;

import com.cofeapp.cofeappgw.model.Reward;
import com.cofeapp.cofeappgw.model.User;
import com.cofeapp.cofeappgw.model.response.UserGwResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserGwResponseConverter {

    public UserGwResponse convert(User user, List<Reward> rewardList) {
        return UserGwResponse.builder()
                .id(user.getId())
                .country(user.getCountry())
                .name(user.getName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .rewardList(rewardList)
                .build();
    }
}
