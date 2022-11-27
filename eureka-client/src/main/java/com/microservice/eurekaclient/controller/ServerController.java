package com.microservice.eurekaclient.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.eurekaclient.entity.Server;
import com.microservice.eurekaclient.service.ServerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api/v1")
@Slf4j
public class ServerController {

    private final ServerService serverService;

    @Autowired
    public ServerController(ServerService serverService) {
        this.serverService = serverService;
    }

    @GetMapping("/servers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Server>> getAllPermutations() {
        log.info("Get all servers");
        return ResponseEntity.status(HttpStatus.OK).body(
            serverService.getAllServers());
    }
}
