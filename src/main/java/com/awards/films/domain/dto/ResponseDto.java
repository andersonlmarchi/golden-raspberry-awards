package com.awards.films.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseDto {

    @JsonProperty("min")
    private Iterable<IntervalDto> min;

    @JsonProperty("max")
    private Iterable<IntervalDto> max;

}
