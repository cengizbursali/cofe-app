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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RewardServiceTest {

    @InjectMocks
    private RewardService rewardService;

    @Mock
    private RewardConverter rewardConverter;

    @Mock
    private RewardDtoConverter rewardDtoConverter;

    @Mock
    private RewardRepository rewardRepository;

    @Mock
    private UserRewardRepository userRewardRepository;

    @Test
    public void it_should_create_reward() {
        // Given
        RewardCreateRequest rewardCreateRequest = RewardCreateRequest.builder().build();
        Reward reward = Reward.builder().build();
        when(rewardConverter.convert(rewardCreateRequest)).thenReturn(reward);

        // When
        rewardService.create(rewardCreateRequest);

        // Then
        verify(rewardConverter).convert(rewardCreateRequest);
        verify(rewardRepository).save(reward);
    }

    @Test
    public void it_should_get_all_when_user_id_is_null() {
        // Given
        Reward reward1 = Reward.builder().build();
        Reward reward2 = Reward.builder().build();
        when(rewardRepository.findAll()).thenReturn(Arrays.asList(reward1, reward2));
        RewardDto rewardDto1 = RewardDto.builder().build();
        when(rewardDtoConverter.convert(reward1)).thenReturn(rewardDto1);
        RewardDto rewardDto2 = RewardDto.builder().build();
        when(rewardDtoConverter.convert(reward2)).thenReturn(rewardDto2);

        // When
        List<RewardDto> rewardDtoList = rewardService.getAllByFilter(null);

        // Then
        verify(rewardRepository).findAll();
        verifyNoInteractions(userRewardRepository);
        verify(rewardDtoConverter).convert(reward1);
        verify(rewardDtoConverter).convert(reward2);
        assertThat(rewardDtoList).containsExactlyInAnyOrder(rewardDto1, rewardDto2);
    }

    @Test
    public void it_should_get_all_when_user_id_is_not_null() {
        // Given
        UserReward userReward = UserReward.builder().rewardId(33).userId(1).build();
        when(userRewardRepository.findAllByUserId(1)).thenReturn(Collections.singletonList(userReward));
        Reward reward1 = Reward.builder().build();
        when(rewardRepository.findAllByIdIn(List.of(33))).thenReturn(List.of(reward1));
        RewardDto rewardDto1 = RewardDto.builder().build();
        when(rewardDtoConverter.convert(reward1)).thenReturn(rewardDto1);

        // When
        List<RewardDto> rewardDtoList = rewardService.getAllByFilter(1);

        // Then
        verify(userRewardRepository).findAllByUserId(1);
        verify(rewardRepository).findAllByIdIn(Collections.singletonList(33));
        verify(rewardDtoConverter).convert(reward1);
        assertThat(rewardDtoList).containsExactly(rewardDto1);
    }


    @Test
    public void it_should_get_reward_by_id() {
        // Given
        Reward reward = Reward.builder().build();
        when(rewardRepository.findById(33)).thenReturn(Optional.of(reward));
        RewardDto rewardDto = RewardDto.builder().build();
        when(rewardDtoConverter.convert(reward)).thenReturn(rewardDto);

        // When
        RewardDto result = rewardService.getById(33);

        // Then
        verify(rewardRepository).findById(33);
        verify(rewardDtoConverter).convert(reward);
        assertThat(result).isEqualTo(rewardDto);
    }

    @Test
    public void it_should_throw_exception_when_reward_not_found_by_id() {
        // Given
        when(rewardRepository.findById(33)).thenReturn(Optional.empty());

        // When
        Throwable throwable = catchThrowable(() -> rewardService.getById(33));

        // Then
        assertThat(throwable).isInstanceOf(RewardServiceException.class);
        RewardServiceException exception = (RewardServiceException) throwable;
        assertThat(exception.getKey()).isEqualTo("reward.not.found");
        verify(rewardRepository).findById(33);
        verifyNoInteractions(rewardDtoConverter);
    }
}