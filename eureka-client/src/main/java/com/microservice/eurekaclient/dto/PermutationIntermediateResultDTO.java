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
public class PermutationIntermediateResultDTO {
    private String id;
    private LocalDateTime startCalculation;
}
