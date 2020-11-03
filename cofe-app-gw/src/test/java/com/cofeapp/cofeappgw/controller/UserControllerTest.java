package com.cofeapp.cofeappgw.controller;

import com.cofeapp.cofeappgw.model.User;
import com.cofeapp.cofeappgw.model.enums.Country;
import com.cofeapp.cofeappgw.model.request.UserCreateRequest;
import com.cofeapp.cofeappgw.model.response.UserGwResponse;
import com.cofeapp.cofeappgw.service.UserRewardService;
import com.cofeapp.cofeappgw.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRewardService userRewardService;

    @Test
    public void it_should_create_user() throws Exception {
        // Given
        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .country(Country.TURKEY)
                .email("cengizbursali@gmail.com")
                .name("cengiz")
                .phone("+905427609780")
                .build();

        // When
        ResultActions resultActions = mockMvc.perform(post("/users")
                                                              .contentType(MediaType.APPLICATION_JSON)
                                                              .content(new ObjectMapper().writeValueAsString(userCreateRequest)));

        // Then
        resultActions.andExpect(status().isCreated());
        ArgumentCaptor<UserCreateRequest> userCreateRequestArgumentCaptor = ArgumentCaptor.forClass(UserCreateRequest.class);
        verify(userService).create(userCreateRequestArgumentCaptor.capture());
        assertThat(userCreateRequestArgumentCaptor.getValue()).usingRecursiveComparison().isEqualTo(userCreateRequest);
    }

    @Test
    public void it_should_get_all_user() throws Exception {
        // Given
        User user1 = User.builder()
                .id(1)
                .name("name1")
                .email("email1")
                .phone("phone1")
                .country(Country.TURKEY)
                .build();

        User user2 = User.builder()
                .id(2)
                .name("name2")
                .email("email2")
                .phone("phone2")
                .country(Country.KUWAIT)
                .build();

        when(userService.getAll()).thenReturn(Arrays.asList(user1, user2));

        // When
        ResultActions resultActions = mockMvc.perform(get("/users"));

        // Then
        verify(userService).getAll();
        resultActions.andExpect(status().isOk());

        resultActions.andExpect(jsonPath("$[0].id").value(1));
        resultActions.andExpect(jsonPath("$[0].name").value("name1"));
        resultActions.andExpect(jsonPath("$[0].email").value("email1"));
        resultActions.andExpect(jsonPath("$[0].phone").value("phone1"));
        resultActions.andExpect(jsonPath("$[0].country").value("TURKEY"));

        resultActions.andExpect(jsonPath("$[1].id").value(2));
        resultActions.andExpect(jsonPath("$[1].name").value("name2"));
        resultActions.andExpect(jsonPath("$[1].email").value("email2"));
        resultActions.andExpect(jsonPath("$[1].phone").value("phone2"));
        resultActions.andExpect(jsonPath("$[1].country").value("KUWAIT"));
    }

    @Test
    public void it_should_get_by_id() throws Exception {
        // Given
        UserGwResponse userGwResponse = UserGwResponse.builder()
                .id(1)
                .name("name")
                .email("email")
                .phone("phone")
                .country(Country.TURKEY)
                .build();

        when(userRewardService.getUser(33)).thenReturn(userGwResponse);

        // When
        ResultActions resultActions = mockMvc.perform(get("/users/33"));

        // Then
        verify(userRewardService).getUser(33);
        resultActions.andExpect(status().isOk());

        resultActions.andExpect(jsonPath("$.id").value(1));
        resultActions.andExpect(jsonPath("$.name").value("name"));
        resultActions.andExpect(jsonPath("$.email").value("email"));
        resultActions.andExpect(jsonPath("$.phone").value("phone"));
        resultActions.andExpect(jsonPath("$.country").value("TURKEY"));
    }
}