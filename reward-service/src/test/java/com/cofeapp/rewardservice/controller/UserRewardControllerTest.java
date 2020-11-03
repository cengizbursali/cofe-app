package com.cofeapp.rewardservice.controller;

import com.cofeapp.rewardservice.model.request.UserRewardAssignRequest;
import com.cofeapp.rewardservice.service.UserRewardService;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserRewardController.class)
@RunWith(SpringRunner.class)
public class UserRewardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRewardService userRewardService;

    @Test
    public void it_should_assign_reward_to_user() throws Exception {
        // Given
        UserRewardAssignRequest userRewardAssignRequest = UserRewardAssignRequest.builder()
                .userId(1)
                .rewardId(2)
                .build();

        // When
        ResultActions resultActions = mockMvc.perform(post("/user-rewards")
                                                              .contentType(MediaType.APPLICATION_JSON)
                                                              .content(new ObjectMapper().writeValueAsString(userRewardAssignRequest)));

        // Then
        resultActions.andExpect(status().isCreated());
        ArgumentCaptor<UserRewardAssignRequest> userRewardAssignRequestArgumentCaptor = ArgumentCaptor.forClass(UserRewardAssignRequest.class);
        verify(userRewardService).assign(userRewardAssignRequestArgumentCaptor.capture());
        assertThat(userRewardAssignRequestArgumentCaptor.getValue()).usingRecursiveComparison().isEqualTo(userRewardAssignRequest);
    }

    @Test
    public void it_should_throw_exception_when_user_id_is_null() throws Exception {
        // Given
        UserRewardAssignRequest userRewardAssignRequest = UserRewardAssignRequest.builder()
                .userId(null)
                .rewardId(2)
                .build();

        // When
        ResultActions resultActions = mockMvc.perform(post("/user-rewards")
                                                              .contentType(MediaType.APPLICATION_JSON)
                                                              .content(new ObjectMapper().writeValueAsString(userRewardAssignRequest)));

        // Then
        resultActions.andExpect(status().isBadRequest());
        verifyNoInteractions(userRewardService);
    }

    @Test
    public void it_should_throw_exception_when_reward_id_is_null() throws Exception {
        // Given
        UserRewardAssignRequest userRewardAssignRequest = UserRewardAssignRequest.builder()
                .userId(1)
                .rewardId(null)
                .build();

        // When
        ResultActions resultActions = mockMvc.perform(post("/user-rewards")
                                                              .contentType(MediaType.APPLICATION_JSON)
                                                              .content(new ObjectMapper().writeValueAsString(userRewardAssignRequest)));

        // Then
        resultActions.andExpect(status().isBadRequest());
        verifyNoInteractions(userRewardService);
    }

    @Test
    public void it_should_get_user_id_list_by_reward_id() throws Exception {
        // Given
        when(userRewardService.getUserIdListByRewardId(5)).thenReturn(List.of(1, 2, 4));

        // When
        ResultActions resultActions = mockMvc.perform(get("/user-rewards?rewardId=5"));

        // Then
        verify(userRewardService).getUserIdListByRewardId(5);
        resultActions.andExpect(content().string("[1,2,4]"));
    }
}