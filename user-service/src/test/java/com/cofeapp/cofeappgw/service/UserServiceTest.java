package com.cofeapp.cofeappgw.service;

import com.cofeapp.cofeappgw.converter.UserConverter;
import com.cofeapp.cofeappgw.converter.UserDtoConverter;
import com.cofeapp.cofeappgw.entity.User;
import com.cofeapp.cofeappgw.exception.UserServiceException;
import com.cofeapp.cofeappgw.model.request.UserCreateRequest;
import com.cofeapp.cofeappgw.model.response.UserDto;
import com.cofeapp.cofeappgw.repository.UserRepository;
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
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserConverter userConverter;

    @Mock
    private UserDtoConverter userDtoConverter;

    @Mock
    private UserRepository userRepository;

    @Test
    public void it_should_create_user() {
        // Given
        UserCreateRequest userCreateRequest = UserCreateRequest.builder().build();
        User user = User.builder().build();
        when(userConverter.convert(userCreateRequest)).thenReturn(user);

        // When
        userService.create(userCreateRequest);

        // Then
        verify(userConverter).convert(userCreateRequest);
        verify(userRepository).save(user);
    }

    @Test
    public void it_should_get_all_user_when_user_id_list_is_empty() {
        // Given
        User user1 = User.builder().build();
        User user2 = User.builder().build();
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));
        UserDto userDto1 = UserDto.builder().build();
        when(userDtoConverter.convert(user1)).thenReturn(userDto1);
        UserDto userDto2 = UserDto.builder().build();
        when(userDtoConverter.convert(user2)).thenReturn(userDto2);

        // When
        List<UserDto> userDtoList = userService.getAllByFilter(Collections.emptyList());

        // Then
        verify(userRepository).findAll();
        verify(userDtoConverter).convert(user1);
        verify(userDtoConverter).convert(user2);
        assertThat(userDtoList).containsExactlyInAnyOrder(userDto1, userDto2);
    }

    @Test
    public void it_should_get_all_user_when_user_id_list_is_not_empty() {
        // Given
        User user1 = User.builder().build();
        when(userRepository.findAllByIdIn(List.of(1))).thenReturn(Collections.singletonList(user1));
        UserDto userDto1 = UserDto.builder().build();
        when(userDtoConverter.convert(user1)).thenReturn(userDto1);

        // When
        List<UserDto> userDtoList = userService.getAllByFilter(List.of(1));

        // Then
        verify(userRepository).findAllByIdIn(List.of(1));
        verify(userDtoConverter).convert(user1);
        assertThat(userDtoList).containsExactly(userDto1);
    }

    @Test
    public void it_should_get_user_by_id() {
        // Given
        User user = User.builder().build();
        when(userRepository.findById(33)).thenReturn(Optional.of(user));
        UserDto userDto = UserDto.builder().build();
        when(userDtoConverter.convert(user)).thenReturn(userDto);

        // When
        UserDto result = userService.getById(33);

        // Then
        verify(userRepository).findById(33);
        verify(userDtoConverter).convert(user);
        assertThat(result).isEqualTo(userDto);
    }

    @Test
    public void it_should_throw_exception_when_user_not_found_by_id() {
        // Given
        when(userRepository.findById(33)).thenReturn(Optional.empty());

        // When
        Throwable throwable = catchThrowable(() -> userService.getById(33));

        // Then
        assertThat(throwable).isInstanceOf(UserServiceException.class);
        UserServiceException exception = (UserServiceException) throwable;
        assertThat(exception.getKey()).isEqualTo("user.not.found");
        verify(userRepository).findById(33);
        verifyNoInteractions(userDtoConverter);
    }
}