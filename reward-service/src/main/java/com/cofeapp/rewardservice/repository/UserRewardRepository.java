package com.cofeapp.rewardservice.repository;

import com.cofeapp.rewardservice.entity.UserReward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRewardRepository extends JpaRepository<UserReward, Integer> {

    List<UserReward> findAllByUserId(Integer userId);

    List<UserReward> findAllByRewardId(Integer rewardId);
}
