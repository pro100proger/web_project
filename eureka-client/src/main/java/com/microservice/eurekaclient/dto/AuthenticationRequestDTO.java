package com.microservice.eurekaclient.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AuthenticationRequestDTO {
    @NotNull
    // @EmailConstraint
    private String email;

    @NotNull
    // @PasswordConstraint
    private String password;
}
