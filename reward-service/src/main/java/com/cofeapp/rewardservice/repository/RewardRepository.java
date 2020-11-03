package com.cofeapp.rewardservice.repository;

import com.cofeapp.rewardservice.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RewardRepository extends JpaRepository<Reward, Integer> {

    List<Reward> findAllByIdIn(List<Integer> rewardIdList);
}
