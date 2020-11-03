package com.cofeapp.cofeappgw.model.request;

import com.cofeapp.cofeappgw.model.enums.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserCreateRequest {

    private String name;
    private String email;
    private String phone;
    private Country country;
}
