package com.microservice.eurekaclient.service;

import java.util.List;
import java.util.Optional;

import com.microservice.eurekaclient.dto.PermutationDTO;
import com.microservice.eurekaclient.dto.PermutationHistoryDTO;
import com.microservice.eurekaclient.dto.PermutationIntermediateResultDTO;
import com.microservice.eurekaclient.dto.PermutationResultDTO;
import com.microservice.eurekaclient.entity.Permutation;

public interface PermutationService {

    PermutationIntermediateResultDTO calculatePermutationFirstStage(PermutationDTO permutation, String userId);
    PermutationResultDTO getPermutationById(String id);
    List<PermutationHistoryDTO> getAllPermutationsByUserId(String userId);
}
