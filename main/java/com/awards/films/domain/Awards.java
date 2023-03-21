package com.awards.films.domain;

import org.springframework.data.annotation.Id;

public record Awards(@Id Integer id, int year, String title, String studios, String producers, boolean winner) {}
