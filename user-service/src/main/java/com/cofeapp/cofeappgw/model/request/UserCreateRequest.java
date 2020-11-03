package com.cofeapp.cofeappgw.model.request;

import com.cofeapp.cofeappgw.model.enums.Country;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserCreateRequest {

    @NotBlank(message = "userCreateRequest.name.notBlank")
    private String name;

    @NotBlank(message = "userCreateRequest.email.notBlank")
    @Email(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "userCreateRequest.email.format")
    private String email;

    @NotBlank(message = "userCreateRequest.phone.notBlank")
    private String phone;

    @NotNull(message = "userCreateRequest.country.notNull")
    private Country country;
}
