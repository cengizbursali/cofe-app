package com.cofeapp.cofeappgw.service;

import com.cofeapp.cofeappgw.client.RewardServiceClient;
import com.cofeapp.cofeappgw.model.Reward;
import com.cofeapp.cofeappgw.model.request.RewardCreateRequest;
import com.cofeapp.cofeappgw.model.request.UserRewardAssignRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RewardServiceTest {

    @InjectMocks
    private RewardService rewardService;

    @Mock
    private RewardServiceClient rewardServiceClient;

    @Test
    public void it_should_create() {
        // Given
        RewardCreateRequest rewardCreateRequest = RewardCreateRequest.builder().build();

        // When
        rewardService.create(rewardCreateRequest);

        // Then
        verify(rewardServiceClient).create(rewardCreateRequest);
    }

    @Test
    public void it_should_get_by_id() {
        // Given
        Reward reward = Reward.builder().build();
        when(rewardServiceClient.get(11)).thenReturn(reward);

        // When
        Reward result = rewardService.getById(11);

        // Then
        verify(rewardServiceClient).get(11);
        assertThat(result).isEqualTo(reward);
    }

    @Test
    public void it_should_get_all_reward_by_user_id() {
        // Given
        Reward reward = Reward.builder().build();
        List<Reward> rewardList = Collections.singletonList(reward);
        when(rewardServiceClient.getAllByUserId(11)).thenReturn(rewardList);

        // When
        List<Reward> result = rewardService.getAllByUserId(11);

        // Then
        verify(rewardServiceClient).getAllByUserId(11);
        assertThat(result).isEqualTo(rewardList);
    }

    @Test
    public void it_should_get_all() {
        // Given
        Reward reward = Reward.builder().build();
        List<Reward> rewardList = Collections.singletonList(reward);
        when(rewardServiceClient.getAll()).thenReturn(rewardList);

        // When
        List<Reward> result = rewardService.getAll();

        // Then
        verify(rewardServiceClient).getAll();
        assertThat(result).isEqualTo(rewardList);
    }

    @Test
    public void it_should_get_user_id_list_by_reward_id() {
        // Given
        List<Integer> userIdList = Arrays.asList(100, 200);
        when(rewardServiceClient.getUserIdListByRewardId(44)).thenReturn(userIdList);

        // When
        List<Integer> result = rewardService.getUserIdListByRewardId(44);

        // Then
        verify(rewardServiceClient).getUserIdListByRewardId(44);
        assertThat(result).isEqualTo(userIdList);
    }

    @Test
    public void it_should_assign() {
        // Given
        UserRewardAssignRequest userRewardAssignRequest = UserRewardAssignRequest.builder().build();

        // When
        rewardService.assignUser(userRewardAssignRequest);

        // Then
        verify(rewardServiceClient).assignUser(userRewardAssignRequest);
    }
}