package com.cofeapp.rewardservice.converter;

import com.cofeapp.rewardservice.entity.Reward;
import com.cofeapp.rewardservice.model.response.RewardDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RewardDtoConverterTest {

    @InjectMocks
    private RewardDtoConverter rewardDtoConverter;

    @Test
    public void it_should_convert_reward_to_rewardDto() {
        // Given
        LocalDateTime now = LocalDateTime.now();
        Reward reward = Reward.builder()
                .id(1)
                .name("reward name")
                .amount(BigDecimal.TEN)
                .expiryDate(now)
                .build();

        // When
        RewardDto rewardDto = rewardDtoConverter.convert(reward);

        // Then
        assertThat(rewardDto.getId()).isEqualTo(1);
        assertThat(rewardDto.getName()).isEqualTo("reward name");
        assertThat(rewardDto.getAmount()).isEqualTo(BigDecimal.TEN);
        assertThat(rewardDto.getExpiryDate()).isEqualTo(now);
    }
}