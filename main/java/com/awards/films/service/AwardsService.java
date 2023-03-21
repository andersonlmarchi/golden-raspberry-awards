package com.awards.films.service;

import com.awards.films.domain.dto.IntervalDto;
import com.awards.films.domain.dto.QueryResultDto;
import com.awards.films.domain.dto.ResponseDto;
import com.awards.films.domain.dto.WinnersDto;
import com.awards.films.repository.AwardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

@Service
public class AwardsService {

    @Autowired
    private AwardsRepository repository;

    public ResponseDto getAwards() {
        Iterable<QueryResultDto> winnersList = repository.winnersList();

        return new ResponseDto(getInterval(winnersList, true), getInterval(winnersList, false));
    }

    private List<IntervalDto> getInterval(Iterable<QueryResultDto> winnersList, boolean isMinInterval) {
        List<IntervalDto> response = new ArrayList<>();
        int minInterval = Integer.MAX_VALUE;
        int maxInterval = Integer.MIN_VALUE;

        for (WinnersDto winner : getWinnerListByYear(getProducerWinnersList(winnersList), winnersList)) {
            List<Integer> years = winner.getYears().stream().sorted().toList();

            if (years.size() > 1) {
                int interval = years.get(1) - years.get(0);

                minInterval = Math.min(interval, minInterval);
                maxInterval = Math.max(interval, maxInterval);

                int previousWin = years.get(0);
                int followingWin = years.get(1);

                for (int i = 2; i < years.size(); i++) {
                    int currentInterval = years.get(i) - years.get(i - 1);

                    previousWin = years.get(i - 1);
                    followingWin = years.get(i);

                    if (isMinInterval && currentInterval < interval) {
                        interval = currentInterval;
                        minInterval = Math.min(interval, minInterval);
                    } else if (!isMinInterval && currentInterval > interval) {
                        interval = currentInterval;
                        maxInterval = Math.max(interval, maxInterval);
                    }
                }
                response.add(new IntervalDto(interval, winner.getProducer(), previousWin, followingWin));
            }
        }

        int finalInterval = (isMinInterval) ? minInterval : maxInterval;
        return response.stream().filter(res -> res.getInterval() == finalInterval).collect(Collectors.toList());
    }

    private Set<String> getProducerWinnersList(Iterable<QueryResultDto> winnersList) {
        return StreamSupport.stream(winnersList.spliterator(), false)
                .flatMap(winner -> Arrays.stream(winner.getProducers().split("(,\\sand\\s|,\\s|\\sand\\s)")))
                .map(String::trim)
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private List<WinnersDto> getWinnerListByYear(Set<String> producers, Iterable<QueryResultDto> winnersList) {
        return producers.stream().map(producer -> {
            List<Integer> years = StreamSupport.stream(winnersList.spliterator(), false)
                    .filter(winner -> winner.getProducers().contains(producer))
                    .map(QueryResultDto::getYear)
                    .collect(Collectors.toList());
            return new WinnersDto(producer, years);
        }).collect(Collectors.toList());
    }


}
