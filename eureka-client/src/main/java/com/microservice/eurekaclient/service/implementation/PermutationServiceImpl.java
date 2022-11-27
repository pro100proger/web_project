package com.microservice.eurekaclient.service.implementation;

import java.io.Console;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.microservice.eurekaclient.dto.PermutationDTO;
import com.microservice.eurekaclient.dto.PermutationHistoryDTO;
import com.microservice.eurekaclient.dto.PermutationIntermediateResultDTO;
import com.microservice.eurekaclient.dto.PermutationResultDTO;
import com.microservice.eurekaclient.entity.Permutation;
import com.microservice.eurekaclient.entity.Server;
import com.microservice.eurekaclient.entity.User;
import com.microservice.eurekaclient.mapper.PermutationMapper;
import com.microservice.eurekaclient.repository.PermutationRepository;
import com.microservice.eurekaclient.repository.ServerRepository;
import com.microservice.eurekaclient.repository.UserRepository;
import com.microservice.eurekaclient.service.PermutationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PermutationServiceImpl implements PermutationService {

    private final PermutationRepository permutationRepository;
    private final UserRepository userRepository;
    private final ServerRepository serverRepository;
    private final PermutationMapper permutationMapper;
    private static final AtomicInteger tasksInProgress = new AtomicInteger(0);
    @Value("${eureka.instance.instance-id}")
    private String serverName;

    @Autowired
    public PermutationServiceImpl(PermutationRepository permutationRepository, UserRepository userRepository, ServerRepository serverRepository, PermutationMapper permutationMapper) {
        this.permutationRepository = permutationRepository;
        this.userRepository = userRepository;
        this.serverRepository = serverRepository;
        this.permutationMapper = permutationMapper;
    }

    private static void swap(String[] input, int a, int b) {
        String temp = input[a];
        input[a] = input[b];
        input[b] = temp;
    }

    public PermutationIntermediateResultDTO calculatePermutationFirstStage(PermutationDTO permutationData, String userId) {
        log.info("ServiceImpl: Get permutation by id");

        Server server = new Server(serverName);
        if (tasksInProgress.get() > 3) {
            throw new RuntimeException();
        }
        tasksInProgress.incrementAndGet();
        server.setTasksInProgress(tasksInProgress.get());
        serverRepository.save(server);

        LocalDateTime startCalculation = LocalDateTime.now();
        PermutationIntermediateResultDTO firstStage = new PermutationIntermediateResultDTO();
        firstStage.setStartCalculation(startCalculation);
        Permutation permutation = new Permutation(permutationData.getData(), startCalculation);

        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        permutation.setUser(user);
        permutationRepository.save(permutation);

        long start = System.currentTimeMillis();

        new Thread(() -> {
            calculatePermutationSecondStage(permutation, start, server);
        }).start();

        firstStage.setId(permutation.getId());
        return firstStage;
    }

    public PermutationResultDTO calculatePermutationSecondStage(Permutation permutation, long start, Server server) {
        PermutationResultDTO secondStage = new PermutationResultDTO();

        int n = permutation.getData().length();

        String[] elements = permutation.getData().split("");

        int[] indexes = new int[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = 0;
        }

        List<String> result = new ArrayList<>();

        StringBuilder temp = new StringBuilder();
        for (String element : elements) {
            temp.append(element);
        }
        result.add(temp.toString());
        temp = new StringBuilder("");

        int i = 0;
        while (i < n) {
            if (indexes[i] < i) {
                swap(elements, i % 2 == 0 ? 0 : indexes[i], i);
                for (String element : elements) {
                    temp.append(element);
                }
                result.add(temp.toString());
                temp = new StringBuilder("");

                indexes[i]++;
                i = 0;
            } else {
                indexes[i] = 0;
                i++;
            }
        }
        long finish = System.currentTimeMillis();
        long duration = finish - start;

        secondStage.setDuration(duration);
        permutation.setDuration(duration);

        permutation.setOutputData(result);

        LocalDateTime endCalculation =  LocalDateTime.now().plusSeconds(duration);

        secondStage.setEndCalculation(endCalculation);
        permutation.setEndCalculation(endCalculation);

        permutationRepository.save(permutation);

        tasksInProgress.decrementAndGet();
        server.setTasksInProgress(tasksInProgress.get());
        serverRepository.save(server);
        return secondStage;
    }

    public PermutationResultDTO getPermutationById(String id) {
        Permutation permutation = permutationRepository.getPermutationById(id);
        log.info("Get permutation by id in service");
        if (!permutationRepository.existsById(id)) {
            log.error(String.format("Article not found by id: %s", id));
            throw new RuntimeException();
        }
        return permutationMapper.entityToResultDto(permutation);
    }

    @Override
    public List<PermutationHistoryDTO> getAllPermutationsByUserId(String userId) {
        List<Permutation> permutations = permutationRepository.getAllPermutationsByUserId(userId).orElseThrow(EntityNotFoundException::new);
        log.info("Get all permutations by user id {} ", userId);
        return permutations.stream().map(permutationMapper::entityToHistoryDto).toList();
    }
}
