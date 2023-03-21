package com.awards.films.domain.dto;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class AwardsDto {

    private int id;

    private int year;

    private String title;

    private String studios;

    private String producers;

    private String winner;

}
