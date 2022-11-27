package com.microservice.eurekaclient.service.implementation;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import com.microservice.eurekaclient.dto.UserDTO;
import com.microservice.eurekaclient.entity.ConfirmationToken;
import com.microservice.eurekaclient.entity.User;
import com.microservice.eurekaclient.mapper.UserMapper;
import com.microservice.eurekaclient.repository.UserRepository;
import com.microservice.eurekaclient.security.PasswordConfig;
import com.microservice.eurekaclient.service.ConfirmationTokenService;
import com.microservice.eurekaclient.service.UserService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordConfig passwordConfig;
    private final UserMapper userMapper;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public String signUpUser(UserDTO userDTO) {

        log.info(String.format("Service: signing up user with the email %s", userDTO.getEmail()));

        User user = userMapper.dtoToEntity(userDTO);
        boolean userExists = userRepository
            .findByEmail(userDTO.getEmail())
            .isPresent();

        if (userExists) {
            log.error(String.format("Service: email %s already taken", userDTO.getEmail()));
            throw new RuntimeException();
        }

        String encodedPassword = passwordConfig.passwordEncoder()
            .encode(userDTO.getPassword());

        user.setPassword(encodedPassword);

        log.info(String.format("Service: saving user with the email %s", userDTO.getEmail()));
        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(
            token,
            LocalDateTime.now(),
            LocalDateTime.now().plusMinutes(15),
            user
        );

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        return token;
    }

    @Override
    public void enableUser(String email) {
        log.debug(String.format("enabling user with the email %s", email));
        userRepository.enableUser(email);
    }

    @Override
    public User findUserByEmail(String email) {
        log.info(String.format("find user with the email %s", email));
        return userRepository.findByEmail(email).
            orElseThrow(EntityNotFoundException::new);
    }
}