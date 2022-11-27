package com.microservice.eurekaclient.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class PermutationResultDTO {
    private long duration;
    private LocalDateTime endCalculation;
}
