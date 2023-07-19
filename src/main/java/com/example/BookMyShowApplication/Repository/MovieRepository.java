package com.example.BookMyShowApplication.Repository;

import com.example.BookMyShowApplication.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

}
