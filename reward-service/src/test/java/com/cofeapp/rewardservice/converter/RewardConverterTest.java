package com.cofeapp.rewardservice.converter;

import com.cofeapp.rewardservice.entity.Reward;
import com.cofeapp.rewardservice.model.request.RewardCreateRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RewardConverterTest {

    @InjectMocks
    private RewardConverter rewardConverter;

    @Test
    public void it_should_convert_createRewardRequest_to_reward() {
        // Given
        LocalDateTime now = LocalDateTime.now();
        RewardCreateRequest rewardCreateRequest = RewardCreateRequest.builder()
                .name("reward name")
                .amount(BigDecimal.TEN)
                .expiryDate(now)
                .build();

        // When
        Reward reward = rewardConverter.convert(rewardCreateRequest);

        // Then
        assertThat(reward.getName()).isEqualTo("reward name");
        assertThat(reward.getAmount()).isEqualTo(BigDecimal.TEN);
        assertThat(reward.getExpiryDate()).isEqualTo(now);
    }
}