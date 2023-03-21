package com.awards.films.domain.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class QueryResultDto {

    private int year;

    private String producers;

}
