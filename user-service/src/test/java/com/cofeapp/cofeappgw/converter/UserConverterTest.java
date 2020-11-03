package com.cofeapp.cofeappgw.converter;

import com.cofeapp.cofeappgw.entity.User;
import com.cofeapp.cofeappgw.model.enums.Country;
import com.cofeapp.cofeappgw.model.request.UserCreateRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserConverterTest {

    @InjectMocks
    private UserConverter userConverter;

    @Test
    public void it_should_convert_userCreateRequest_to_user() {
        // Given
        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .country(Country.TURKEY)
                .email("cengizbursali@gmail.com")
                .name("cengiz")
                .phone("+905427609780")
                .build();

        // When
        User user = userConverter.convert(userCreateRequest);

        // Then
        assertThat(user.getCountry()).isEqualTo(Country.TURKEY);
        assertThat(user.getEmail()).isEqualTo("cengizbursali@gmail.com");
        assertThat(user.getName()).isEqualTo("cengiz");
        assertThat(user.getPhone()).isEqualTo("+905427609780");
    }
}