package com.microservice.eurekaclient.service;

import java.util.List;
import java.util.Optional;

import com.microservice.eurekaclient.entity.Server;

public interface ServerService {

    List<Server> getAllServers();
}
