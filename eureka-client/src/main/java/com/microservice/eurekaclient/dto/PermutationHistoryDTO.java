package com.microservice.eurekaclient.dto;

import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class PermutationHistoryDTO {
    private String data;
    private LocalDateTime startCalculation;
    private LocalDateTime endCalculation;
    private long duration;

    public PermutationHistoryDTO(PermutationHistoryDTO permutationHistoryDTO) {
        this.data = permutationHistoryDTO.getData();
        this.startCalculation = permutationHistoryDTO.getStartCalculation();
        this.endCalculation = permutationHistoryDTO.getEndCalculation();
        this.duration = permutationHistoryDTO.getDuration();
    }
}