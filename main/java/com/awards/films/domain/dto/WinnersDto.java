package com.awards.films.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WinnersDto {

    private String producer;

    private List<Integer> years;

}
