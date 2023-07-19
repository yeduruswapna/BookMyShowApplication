package com.example.BookMyShowApplication.Services;

import com.example.BookMyShowApplication.Dtos.RequestDtos.MovieEntryDto;
import com.example.BookMyShowApplication.Models.Movie;
import com.example.BookMyShowApplication.Repository.MovieRepository;
import com.example.BookMyShowApplication.Transformers.MovieTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieEntryDto movieEntryDto) {
        Movie movie= MovieTransformer.convertDtoToEntity(movieEntryDto);
        movieRepository.save(movie);
        return "Movie Added Successfully";
    }
}
