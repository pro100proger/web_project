package com.microservice.eurekaclient.service;

import java.util.Optional;

import com.microservice.eurekaclient.entity.ConfirmationToken;

public interface ConfirmationTokenService {
    void saveConfirmationToken(ConfirmationToken token);

    Optional<ConfirmationToken> getToken(String token);

    void setConfirmedAt(String token);
}
