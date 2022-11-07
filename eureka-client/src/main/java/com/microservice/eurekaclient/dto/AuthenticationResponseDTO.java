package com.microservice.eurekaclient.dto;


import com.microservice.eurekaclient.entity.Role;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AuthenticationResponseDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String jwt;
}