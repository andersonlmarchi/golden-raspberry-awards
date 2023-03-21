package com.awards.films.repository;

import com.awards.films.domain.Awards;
import com.awards.films.domain.dto.QueryResultDto;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AwardsRepository extends CrudRepository<Awards, String> {

    @Query("SELECT \"YEAR\", producers FROM AWARDS where winner = true")
    List<QueryResultDto> winnersList();

}
