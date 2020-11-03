package com.cofeapp.cofeappgw.service;

import com.cofeapp.cofeappgw.converter.RewardGwResponseConverter;
import com.cofeapp.cofeappgw.converter.UserGwResponseConverter;
import com.cofeapp.cofeappgw.model.Reward;
import com.cofeapp.cofeappgw.model.User;
import com.cofeapp.cofeappgw.model.request.UserRewardAssignRequest;
import com.cofeapp.cofeappgw.model.response.RewardGwResponse;
import com.cofeapp.cofeappgw.model.response.UserGwResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRewardService {

    private final UserService userService;
    private final RewardService rewardService;
    private final UserGwResponseConverter userGwResponseConverter;
    private final RewardGwResponseConverter rewardGwResponseConverter;

    public UserGwResponse getUser(Integer id) {
        final User user = userService.getById(id);
        final List<Reward> rewardList = rewardService.getAllByUserId(user.getId());
        return userGwResponseConverter.convert(user, rewardList);
    }

    public RewardGwResponse getReward(Integer rewardId) {
        final Reward reward = rewardService.getById(rewardId);
        final List<Integer> userIdList = rewardService.getUserIdListByRewardId(rewardId);
        final List<User> userList = userService.getAllByUserIdList(userIdList);
        return rewardGwResponseConverter.convert(reward, userList);
    }

    public void assignUser(UserRewardAssignRequest userRewardAssignRequest) {
        assertIfUserIsRegistered(userRewardAssignRequest.getUserId());
        rewardService.assignUser(userRewardAssignRequest);
    }

    private void assertIfUserIsRegistered(Integer userId) {
        // throw exception when user not found
        userService.getById(userId);
    }
}
