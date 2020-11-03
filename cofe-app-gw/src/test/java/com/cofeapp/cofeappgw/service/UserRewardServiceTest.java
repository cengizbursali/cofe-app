package com.cofeapp.cofeappgw.service;

import com.cofeapp.cofeappgw.converter.RewardGwResponseConverter;
import com.cofeapp.cofeappgw.converter.UserGwResponseConverter;
import com.cofeapp.cofeappgw.model.Reward;
import com.cofeapp.cofeappgw.model.User;
import com.cofeapp.cofeappgw.model.request.UserRewardAssignRequest;
import com.cofeapp.cofeappgw.model.response.RewardGwResponse;
import com.cofeapp.cofeappgw.model.response.UserGwResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserRewardServiceTest {

    @InjectMocks
    private UserRewardService userRewardService;

    @Mock
    private UserService userService;

    @Mock
    private RewardService rewardService;

    @Mock
    private UserGwResponseConverter userGwResponseConverter;

    @Mock
    private RewardGwResponseConverter rewardGwResponseConverter;

    @Test
    public void it_should_get_user() {
        // Given
        User user = User.builder().id(55).build();
        when(userService.getById(55)).thenReturn(user);
        List<Reward> rewardList = Collections.singletonList(Reward.builder().build());
        when(rewardService.getAllByUserId(55)).thenReturn(rewardList);
        UserGwResponse userGwResponse = UserGwResponse.builder().build();
        when(userGwResponseConverter.convert(user, rewardList)).thenReturn(userGwResponse);

        // When
        UserGwResponse result = userRewardService.getUser(55);

        // Then
        verify(userService).getById(55);
        verify(rewardService).getAllByUserId(55);
        verify(userGwResponseConverter).convert(user, rewardList);
        assertThat(result).isEqualTo(userGwResponse);
    }

    @Test
    public void it_should_get_reward() {
        // Given
        Reward reward = Reward.builder().build();
        when(rewardService.getById(66)).thenReturn(reward);

        when(rewardService.getUserIdListByRewardId(66)).thenReturn(List.of(12, 13));

        User user1 = User.builder().build();
        User user2 = User.builder().build();
        List<User> userList = Arrays.asList(user1, user2);
        when(userService.getAllByUserIdList(List.of(12, 13))).thenReturn(userList);

        RewardGwResponse rewardGwResponse = RewardGwResponse.builder().build();
        when(rewardGwResponseConverter.convert(reward, userList)).thenReturn(rewardGwResponse);

        // When
        RewardGwResponse result = userRewardService.getReward(66);

        // Then
        verify(rewardService).getById(66);
        verify(rewardService).getUserIdListByRewardId(66);
        verify(userService).getAllByUserIdList(List.of(12, 13));
        verify(rewardGwResponseConverter).convert(reward, userList);
        assertThat(result).isEqualTo(rewardGwResponse);
    }

    @Test
    public void it_should_assign_user() {
        // Given
        UserRewardAssignRequest userRewardAssignRequest = UserRewardAssignRequest.builder().userId(1).rewardId(2).build();
        when(userService.getById(1)).thenReturn(User.builder().build());

        // When
        userRewardService.assignUser(userRewardAssignRequest);

        // Then
        verify(userService).getById(1);
        rewardService.assignUser(userRewardAssignRequest);
    }

    @Test
    public void it_should_throw_exception_when_user_not_found() {
        // Given
        UserRewardAssignRequest userRewardAssignRequest = UserRewardAssignRequest.builder().userId(1).rewardId(2).build();
        when(userService.getById(1)).thenThrow(new RuntimeException());

        // When
        catchThrowable(() -> userRewardService.assignUser(userRewardAssignRequest));

        // Then
        verify(userService).getById(1);
        verifyNoInteractions(rewardService);
    }
}