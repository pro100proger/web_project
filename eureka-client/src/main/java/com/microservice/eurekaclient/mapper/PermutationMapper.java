package com.microservice.eurekaclient.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

import com.microservice.eurekaclient.dto.PermutationHistoryDTO;
import com.microservice.eurekaclient.dto.PermutationResultDTO;
import com.microservice.eurekaclient.entity.Permutation;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true), nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PermutationMapper {
    PermutationResultDTO entityToResultDto(Permutation permutation);
    PermutationHistoryDTO entityToHistoryDto(Permutation permutation);
    Permutation historyDtoToEntity(PermutationHistoryDTO permutationHistoryDTO);
}