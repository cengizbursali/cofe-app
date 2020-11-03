package com.cofeapp.rewardservice.service;

import com.cofeapp.rewardservice.entity.UserReward;
import com.cofeapp.rewardservice.exception.RewardServiceException;
import com.cofeapp.rewardservice.model.request.UserRewardAssignRequest;
import com.cofeapp.rewardservice.model.response.RewardDto;
import com.cofeapp.rewardservice.repository.UserRewardRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
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
    private RewardService rewardService;

    @Mock
    private UserRewardRepository userRewardRepository;

    @Test
    public void it_should_assign_reward_to_user() {
        // Given
        UserRewardAssignRequest userRewardAssignRequest = UserRewardAssignRequest.builder()
                .userId(1)
                .rewardId(2)
                .build();
        RewardDto rewardDto = RewardDto.builder().id(2).expiryDate(LocalDateTime.now().plusMonths(1)).build();
        when(rewardService.getById(2)).thenReturn(rewardDto);

        // When
        userRewardService.assign(userRewardAssignRequest);

        // Then
        verify(rewardService).getById(2);
        ArgumentCaptor<UserReward> userRewardArgumentCaptor = ArgumentCaptor.forClass(UserReward.class);
        verify(userRewardRepository).save(userRewardArgumentCaptor.capture());
        assertThat(userRewardArgumentCaptor.getValue().getRewardId()).isEqualTo(2);
        assertThat(userRewardArgumentCaptor.getValue().getUserId()).isEqualTo(1);
    }

    @Test
    public void it_should_throw_exception_when_reward_has_expired() {
        // Given
        UserRewardAssignRequest userRewardAssignRequest = UserRewardAssignRequest.builder()
                .userId(1)
                .rewardId(2)
                .build();
        RewardDto rewardDto = RewardDto.builder().id(2).expiryDate(LocalDateTime.now().minusMonths(1)).build();
        when(rewardService.getById(2)).thenReturn(rewardDto);

        // When
        Throwable throwable = catchThrowable(() -> userRewardService.assign(userRewardAssignRequest));

        // Then
        assertThat(throwable).isInstanceOf(RewardServiceException.class);
        RewardServiceException exception = (RewardServiceException) throwable;
        assertThat(exception.getKey()).isEqualTo("reward.has.expired");
        assertThat(exception.getHttpStatus()).isEqualTo(HttpStatus.BAD_REQUEST);
        verify(rewardService).getById(2);
        verifyNoInteractions(userRewardRepository);
    }

    @Test
    public void it_should_get_user_id_list_by_reward_id() {
        // Given
        UserReward userReward1 = UserReward.builder().rewardId(100).userId(3).build();
        UserReward userReward2 = UserReward.builder().rewardId(100).userId(4).build();
        when(userRewardRepository.findAllByRewardId(100)).thenReturn(Arrays.asList(userReward1, userReward2));

        // When
        List<Integer> userIdList = userRewardService.getUserIdListByRewardId(100);

        // Then
        verify(userRewardRepository).findAllByRewardId(100);
        assertThat(userIdList).containsExactly(3, 4);
    }
}