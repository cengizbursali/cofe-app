package com.cofeapp.rewardservice.controller;

import com.cofeapp.rewardservice.model.request.RewardCreateRequest;
import com.cofeapp.rewardservice.model.response.RewardDto;
import com.cofeapp.rewardservice.service.RewardService;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RewardController.class)
@RunWith(SpringRunner.class)
public class RewardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RewardService rewardService;

    @Test
    public void it_should_create_reward() throws Exception {
        // Given
        LocalDateTime now = LocalDateTime.now();
        RewardCreateRequest rewardCreateRequest = RewardCreateRequest.builder()
                .name("reward name")
                .amount(BigDecimal.TEN)
                .expiryDate(now.plusMonths(12))
                .build();

        // When
        ResultActions resultActions = mockMvc.perform(post("/rewards")
                                                              .contentType(MediaType.APPLICATION_JSON)
                                                              .content(new ObjectMapper().writeValueAsString(rewardCreateRequest)));

        // Then
        resultActions.andExpect(status().isCreated());
        ArgumentCaptor<RewardCreateRequest> rewardCreateRequestArgumentCaptor = ArgumentCaptor.forClass(RewardCreateRequest.class);
        verify(rewardService).create(rewardCreateRequestArgumentCaptor.capture());
        assertThat(rewardCreateRequestArgumentCaptor.getValue()).usingRecursiveComparison().isEqualTo(rewardCreateRequest);
    }

    @Test
    public void it_should_throw_exception_when_name_is_blank() throws Exception {
        // Given
        LocalDateTime now = LocalDateTime.now();
        RewardCreateRequest rewardCreateRequest = RewardCreateRequest.builder()
                .name("")
                .amount(BigDecimal.TEN)
                .expiryDate(now.plusMonths(12))
                .build();

        // When
        ResultActions resultActions = mockMvc.perform(post("/rewards")
                                                              .contentType(MediaType.APPLICATION_JSON)
                                                              .content(new ObjectMapper().writeValueAsString(rewardCreateRequest)));

        // Then
        resultActions.andExpect(status().isBadRequest());
        verifyNoInteractions(rewardService);
    }

    @Test
    public void it_should_get_all_reward() throws Exception {
        // Given
        LocalDateTime expiryDate = LocalDateTime.of(2020, 11, 11, 12, 30);

        RewardDto reward1 = RewardDto.builder()
                .id(1)
                .name("name1")
                .amount(BigDecimal.ONE)
                .expiryDate(expiryDate)
                .build();

        RewardDto reward2 = RewardDto.builder()
                .id(2)
                .name("name2")
                .amount(BigDecimal.TEN)
                .expiryDate(expiryDate)
                .build();

        when(rewardService.getAllByFilter(44)).thenReturn(Arrays.asList(reward1, reward2));

        // When
        ResultActions resultActions = mockMvc.perform(get("/rewards?userId=44"));

        // Then
        verify(rewardService).getAllByFilter(44);
        resultActions.andExpect(status().isOk());

        resultActions.andExpect(jsonPath("$[0].id").value(1));
        resultActions.andExpect(jsonPath("$[0].name").value("name1"));
        resultActions.andExpect(jsonPath("$[0].amount").value(1));
        resultActions.andExpect(jsonPath("$[0].expiryDate").value("2020-11-11T12:30:00"));

        resultActions.andExpect(jsonPath("$[1].id").value(2));
        resultActions.andExpect(jsonPath("$[1].name").value("name2"));
        resultActions.andExpect(jsonPath("$[1].amount").value(10));
        resultActions.andExpect(jsonPath("$[1].expiryDate").value("2020-11-11T12:30:00"));
    }

    @Test
    public void it_should_get_by_id() throws Exception {
        // Given
        LocalDateTime expiryDate = LocalDateTime.of(2020, 11, 11, 12, 30);
        RewardDto rewardDto = RewardDto.builder()
                .id(1)
                .name("name1")
                .amount(BigDecimal.ONE)
                .expiryDate(expiryDate)
                .build();

        when(rewardService.getById(33)).thenReturn(rewardDto);

        // When
        ResultActions resultActions = mockMvc.perform(get("/rewards/33"));

        // Then
        verify(rewardService).getById(33);
        resultActions.andExpect(status().isOk());

        resultActions.andExpect(jsonPath("$.id").value(1));
        resultActions.andExpect(jsonPath("$.name").value("name1"));
        resultActions.andExpect(jsonPath("$.amount").value(1));
        resultActions.andExpect(jsonPath("$.expiryDate").value("2020-11-11T12:30:00"));
    }
}