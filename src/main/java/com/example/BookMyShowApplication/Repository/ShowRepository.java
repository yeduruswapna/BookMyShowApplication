package com.example.BookMyShowApplication.Repository;

import com.example.BookMyShowApplication.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface ShowRepository extends JpaRepository<Show, Integer> {
    @Query(value = "select show_time from shows where show_date like :date% and theater_id = :theaterId and movie_id = :movieId", nativeQuery = true)
    List<Time> getShowTimingsOnDate(String date, Integer theaterId, Integer movieId);

    @Query(value = "select movie_id from shows group by movie_id order by count(*) desc limit 1" , nativeQuery = true)
    Integer getMostShowsMovie();
}
