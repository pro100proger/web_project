package com.microservice.eurekaclient.service;

import java.io.IOException;

import javax.mail.SendFailedException;

import com.microservice.eurekaclient.dto.RegistrationRequestDTO;

public interface RegistrationService {

    public String register(RegistrationRequestDTO request) throws IOException, SendFailedException;

    public String confirmToken(String token);
}
