package com.microservice.eurekaclient.service.implementation;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.eurekaclient.entity.Server;
import com.microservice.eurekaclient.repository.ServerRepository;
import com.microservice.eurekaclient.service.ServerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ServerServiceImpl implements ServerService {

    private final ServerRepository serverRepository;

    @Autowired
    public ServerServiceImpl(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }

    @Override public List<Server> getAllServers() {
        List<Server> servers = serverRepository.findAll();
        log.info("Get all servers");
        return servers;
    }
}
