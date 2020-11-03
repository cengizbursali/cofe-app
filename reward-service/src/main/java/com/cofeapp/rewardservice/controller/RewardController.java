package com.cofeapp.rewardservice.controller;

import com.cofeapp.rewardservice.model.request.RewardCreateRequest;
import com.cofeapp.rewardservice.model.request.RewardFilterRequest;
import com.cofeapp.rewardservice.model.response.RewardDto;
import com.cofeapp.rewardservice.service.RewardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rewards")
public class RewardController {

    private final RewardService rewardService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void create(@RequestBody @Valid RewardCreateRequest rewardCreateRequest) {
        log.info("rewardCreateRequest: {}", rewardCreateRequest);
        rewardService.create(rewardCreateRequest);
    }

    @GetMapping
    public List<RewardDto> getAllByFilter(RewardFilterRequest rewardFilterRequest) {
        return rewardService.getAllByFilter(rewardFilterRequest.getUserId());
    }

    @GetMapping("/{id}")
    public RewardDto getById(@PathVariable Integer id) {
        return rewardService.getById(id);
    }
}
