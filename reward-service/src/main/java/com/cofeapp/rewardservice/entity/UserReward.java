package com.cofeapp.rewardservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_reward", schema = "dbo")
public class UserReward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "id")
    private Integer id;

    @Column(columnDefinition = "user_id")
    private Integer userId;

    @Column(columnDefinition = "reward_id")
    private Integer rewardId;
}
