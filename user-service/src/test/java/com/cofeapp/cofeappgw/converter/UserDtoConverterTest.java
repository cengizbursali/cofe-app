package com.cofeapp.cofeappgw.converter;

import com.cofeapp.cofeappgw.entity.User;
import com.cofeapp.cofeappgw.model.enums.Country;
import com.cofeapp.cofeappgw.model.response.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserDtoConverterTest {

    @InjectMocks
    private UserDtoConverter userDtoConverter;

    @Test
    public void it_should_convert_user_to_userDto() {
        // Given
        User user = User.builder()
                .id(1)
                .country(Country.TURKEY)
                .email("cengizbursali@gmail.com")
                .name("cengiz")
                .phone("+905427609780")
                .build();

        // When
        UserDto userDto = userDtoConverter.convert(user);

        // Then
        assertThat(userDto.getId()).isEqualTo(1);
        assertThat(userDto.getCountry()).isEqualTo(Country.TURKEY);
        assertThat(userDto.getEmail()).isEqualTo("cengizbursali@gmail.com");
        assertThat(userDto.getName()).isEqualTo("cengiz");
        assertThat(userDto.getPhone()).isEqualTo("+905427609780");
    }
}