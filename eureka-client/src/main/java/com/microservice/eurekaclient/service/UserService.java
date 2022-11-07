package com.microservice.eurekaclient.service;
import com.microservice.eurekaclient.dto.UserDTO;
import com.microservice.eurekaclient.entity.User;

public interface UserService {

    String signUpUser(UserDTO userDTO);

    void enableUser(String email);

    User findUserByEmail(String email);

}