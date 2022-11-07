package com.microservice.eurekaclient.dto;

import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequestDTO {
    @NotNull
    // @NameConstraint
    private final String firstName;

    @NotNull
    // @NameConstraint
    private final String lastName;

    @NotNull
    // @EmailConstraint
    private final String email;

    @NotNull
    // @PasswordConstraint
    private final String password;
}