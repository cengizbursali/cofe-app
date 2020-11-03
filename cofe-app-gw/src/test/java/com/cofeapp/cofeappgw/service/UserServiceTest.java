package com.cofeapp.cofeappgw.service;

import com.cofeapp.cofeappgw.client.UserServiceClient;
import com.cofeapp.cofeappgw.model.User;
import com.cofeapp.cofeappgw.model.request.UserCreateRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserServiceClient userServiceClient;

    @Test
    public void it_should_create() {
        // Given
        UserCreateRequest userCreateRequest = UserCreateRequest.builder().build();

        // When
        userService.create(userCreateRequest);

        // Then
        verify(userServiceClient).create(userCreateRequest);
    }

    @Test
    public void it_should_get_by_id() {
        // Given
        User user = User.builder().build();
        when(userServiceClient.get(11)).thenReturn(user);

        // When
        User result = userService.getById(11);

        // Then
        verify(userServiceClient).get(11);
        assertThat(result).isEqualTo(user);
    }

    @Test
    public void it_should_get_all() {
        // Given
        User user1 = User.builder().build();
        User user2 = User.builder().build();
        List<User> userList = Arrays.asList(user1, user2);
        when(userServiceClient.getAll()).thenReturn(userList);

        // When
        List<User> result = userService.getAll();

        // Then
        assertThat(result).isEqualTo(userList);
        verify(userServiceClient).getAll();
    }

    @Test
    public void it_should_get_all_user_by_user_id_list() {
        // Given
        User user1 = User.builder().build();
        User user2 = User.builder().build();
        List<User> userList = Arrays.asList(user1, user2);
        when(userServiceClient.getAllByUserIdList(Arrays.asList(1, 2))).thenReturn(userList);

        // When
        List<User> result = userServiceClient.getAllByUserIdList(Arrays.asList(1, 2));

        // Then
        assertThat(result).isEqualTo(userList);
        verify(userServiceClient).getAllByUserIdList(Arrays.asList(1, 2));
    }

    @Test
    public void it_should_get_all_user_by_id_list() {
        // Given
        User user1 = User.builder().build();
        User user2 = User.builder().build();
        List<User> userList = Arrays.asList(user1, user2);
        when(userServiceClient.getAllByUserIdList(List.of(1, 2))).thenReturn(userList);

        // When
        List<User> result = userService.getAllByUserIdList(List.of(1, 2));

        // Then
        assertThat(result).isEqualTo(userList);
        verify(userServiceClient).getAllByUserIdList(List.of(1, 2));
    }
}