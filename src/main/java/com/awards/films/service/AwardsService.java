package com.awards.films.service;

import com.awards.films.domain.dto.IntervalDto;
import com.awards.films.domain.dto.QueryResultDto;
import com.awards.films.domain.dto.ResponseDto;
import com.awards.films.repository.AwardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        int minInterval = 0;
        int maxInterval = 0;

        for (QueryResultDto winner : winnersList) {
            int[] years = winner.getYears().stream().mapToInt(Integer::intValue).toArray();

            if (years.length > 1) {
                Arrays.sort(years);

                int interval = years[1] - years[0];

                minInterval = interval;
                maxInterval = interval;

                int previousWin = years[0];
                int followingWin = years[1];

                for (int i = 2; i < years.length; i++) {
                    if (isMinInterval) {
                        if (years[i] - years[i - 1] < interval) {
                            interval = years[i] - years[i - 1];
                            previousWin = years[i - 1];
                            followingWin = years[i];

                            minInterval = interval;

                        }
                    } else {
                        if (years[i] - years[i - 1] > interval) {
                            interval = years[i] - years[i - 1];
                            previousWin = years[i - 1];
                            followingWin = years[i];

                            maxInterval = interval;

                        }
                    }
                }
                response.add(new IntervalDto(interval, winner.getProducers(), previousWin, followingWin));
            }
        }
        int finalInterval = (isMinInterval) ? minInterval : maxInterval;
        return response.stream().filter(res -> res.getInterval() == finalInterval).collect(Collectors.toList());
    }

}
