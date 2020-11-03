package com.cofeapp.rewardservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRewardAssignRequest {

    @NotNull(message = "userRewardAssignRequest.userId.notNull")
    private Integer userId;

    @NotNull(message = "userRewardAssignRequest.rewardId.notNull")
    private Integer rewardId;
}
