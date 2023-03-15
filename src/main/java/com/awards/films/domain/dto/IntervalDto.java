package com.awards.films.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IntervalDto {

    @JsonProperty("interval")
    private int interval;

    @JsonProperty("producer")
    private String producer;

    @JsonProperty("previousWin")
    private int previousWin;

    @JsonProperty("followingWin")
    private int followingWin;

}
