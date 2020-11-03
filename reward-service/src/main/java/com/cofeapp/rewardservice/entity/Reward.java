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
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reward", schema = "dbo")
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "id")
    private Integer id;

    @Column(columnDefinition = "name")
    private String name;

    @Column(columnDefinition = "amount")
    private BigDecimal amount;

    @Column(columnDefinition = "expiry_date")
    private LocalDateTime expiryDate;
}
