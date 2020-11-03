package com.cofeapp.cofeappgw.converter;

import com.cofeapp.cofeappgw.model.Reward;
import com.cofeapp.cofeappgw.model.User;
import com.cofeapp.cofeappgw.model.response.RewardGwResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RewardGwResponseConverterTest {

    @InjectMocks
    private RewardGwResponseConverter rewardGwResponseConverter;

    @Test
    public void it_should_convert() {
        // Given
        LocalDateTime now = LocalDateTime.now();
        Reward reward = Reward.builder()
                .id(1)
                .name("reward name")
                .amount(BigDecimal.TEN)
                .expiryDate(now)
                .build();
        List<User> userList = Collections.singletonList(User.builder().build());

        // When
        RewardGwResponse rewardGwResponse = rewardGwResponseConverter.convert(reward, userList);

        // Then
        assertThat(rewardGwResponse.getId()).isEqualTo(1);
        assertThat(rewardGwResponse.getAmount()).isEqualTo(BigDecimal.TEN);
        assertThat(rewardGwResponse.getName()).isEqualTo("reward name");
        assertThat(rewardGwResponse.getExpiryDate()).isEqualTo(now);
        assertThat(rewardGwResponse.getUserList()).isEqualTo(userList);

    }
}