package com.microservice.eurekaclient.service.implementation;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.microservice.eurekaclient.entity.ConfirmationToken;
import com.microservice.eurekaclient.repository.ConfirmationTokenRepository;
import com.microservice.eurekaclient.service.ConfirmationTokenService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    public void saveConfirmationToken(ConfirmationToken token) {
        log.info(String.format("Service: saving token %s", token.getToken()));
        confirmationTokenRepository.save(token);
    }

    @Override
    public Optional<ConfirmationToken> getToken(String token) {
        log.debug(String.format("Service: getting token %s", token));
        return confirmationTokenRepository.findByToken(token);
    }

    @Override
    public void setConfirmedAt(String token) {
        log.debug(String.format("Service: confirming token %s", token));
        confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
