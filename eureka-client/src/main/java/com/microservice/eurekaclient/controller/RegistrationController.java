package com.microservice.eurekaclient.controller;

import java.io.IOException;

import javax.mail.SendFailedException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.eurekaclient.dto.RegistrationRequestDTO;
import com.microservice.eurekaclient.service.RegistrationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api/v1/registration")
@Slf4j
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping()
    public ResponseEntity<String> register(@RequestBody @Valid RegistrationRequestDTO request)
            throws IOException, SendFailedException {
        log.info(String.format("Controller: registering user with email %s", request.getEmail()));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(registrationService.register(request));
    }

    @GetMapping(path = "/confirm")
    public ResponseEntity<String> confirm(@RequestParam("token") String token) {
        log.info(String.format("Controller: confirming token %s", token));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(registrationService.confirmToken(token));
    }

}

