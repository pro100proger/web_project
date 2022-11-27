package com.microservice.eurekaclient.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.eurekaclient.dto.PermutationDTO;
import com.microservice.eurekaclient.dto.PermutationHistoryDTO;
import com.microservice.eurekaclient.dto.PermutationIntermediateResultDTO;
import com.microservice.eurekaclient.dto.PermutationResultDTO;
import com.microservice.eurekaclient.entity.User;
import com.microservice.eurekaclient.service.PermutationService;
import com.microservice.eurekaclient.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/api/v1")
@Slf4j
public class PermutationController {
    private final PermutationService permutationService;
    private final UserService userService;

    @Autowired
    public PermutationController(PermutationService permutationService, UserService userService) {
        this.permutationService = permutationService;
        this.userService = userService;
    }

    @PostMapping(path = "/permutation")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<PermutationIntermediateResultDTO> calculatePermutationFirstStage(@RequestBody PermutationDTO data, @NotNull Principal principal) {
        log.info("Calculate permutation");
        String email = principal.getName();
        User user = userService.findUserByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(
            permutationService.calculatePermutationFirstStage(data, user.getId()));
    }

    @GetMapping("/permutation/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<PermutationResultDTO> getPermutationById(@PathVariable String id) {
        log.info("Get permutation by id {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(
            permutationService.getPermutationById(id));
    }

    @GetMapping("/permutations")
    @PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
    public ResponseEntity<List<PermutationHistoryDTO>> getAllPermutations(@NotNull Principal principal) {
        String email = principal.getName();
        log.info("Get all permutations of the user with an email under {} subscription", email);
        User user = userService.findUserByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(
            permutationService.getAllPermutationsByUserId(user.getId()));
    }
}
