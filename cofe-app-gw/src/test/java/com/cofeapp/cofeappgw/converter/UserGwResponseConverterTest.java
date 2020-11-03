package com.cofeapp.cofeappgw.converter;

import com.cofeapp.cofeappgw.model.Reward;
import com.cofeapp.cofeappgw.model.User;
import com.cofeapp.cofeappgw.model.enums.Country;
import com.cofeapp.cofeappgw.model.response.UserGwResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserGwResponseConverterTest {

    @InjectMocks
    private UserGwResponseConverter userGwResponseConverter;

    @Test
    public void it_should_convert() {
        // Given
        User user = User.builder()
                .id(1)
                .country(Country.KUWAIT)
                .name("user name")
                .email("email")
                .phone("phone")
                .build();
        List<Reward> rewardList = Collections.singletonList(Reward.builder().build());

        // When
        UserGwResponse userGwResponse = userGwResponseConverter.convert(user, rewardList);

        // Then
        assertThat(userGwResponse.getId()).isEqualTo(1);
        assertThat(userGwResponse.getName()).isEqualTo("user name");
        assertThat(userGwResponse.getPhone()).isEqualTo("phone");
        assertThat(userGwResponse.getEmail()).isEqualTo("email");
        assertThat(userGwResponse.getCountry()).isEqualTo(Country.KUWAIT);
        assertThat(userGwResponse.getRewardList()).isEqualTo(rewardList);
    }
}