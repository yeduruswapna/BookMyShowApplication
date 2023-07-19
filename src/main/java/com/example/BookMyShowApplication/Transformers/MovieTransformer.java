package com.example.BookMyShowApplication.Transformers;

import com.example.BookMyShowApplication.Dtos.RequestDtos.MovieEntryDto;
import com.example.BookMyShowApplication.Models.Movie;
import com.example.BookMyShowApplication.Services.MovieService;
import lombok.Data;

@Data
public class MovieTransformer {
    public static Movie convertDtoToEntity(MovieEntryDto movieEntryDto){
        Movie movie= Movie.builder()
                .movieName(movieEntryDto.getMovieName())
                .duration(movieEntryDto.getDuration())
                .genre(movieEntryDto.getGenre())
                .rating(movieEntryDto.getRating())
                .releaseDate(movieEntryDto.getReleaseDate())
                .language(movieEntryDto.getLanguage())
                .build();
        return movie;
    }
}
