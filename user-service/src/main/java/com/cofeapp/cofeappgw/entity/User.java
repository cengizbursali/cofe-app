package com.cofeapp.cofeappgw.entity;

import com.cofeapp.cofeappgw.model.enums.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", schema = "dbo")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "id")
    private Integer id;

    @Column(columnDefinition = "name")
    private String name;

    @Column(columnDefinition = "email")
    private String email;

    @Column(columnDefinition = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "country")
    private Country country;
}
