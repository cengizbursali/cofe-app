package com.cofeapp.cofeappgw.model.response;

import com.cofeapp.cofeappgw.model.Reward;
import com.cofeapp.cofeappgw.model.enums.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserGwResponse {

    private Integer id;
    private String name;
    private String email;
    private String phone;
    private Country country;
    private List<Reward> rewardList;
}
