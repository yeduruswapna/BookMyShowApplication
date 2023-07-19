package com.example.BookMyShowApplication.Dtos.RequestDtos;

import com.example.BookMyShowApplication.Enums.Genre;
import com.example.BookMyShowApplication.Enums.Language;
import lombok.Data;

import java.util.Date;

@Data
public class MovieEntryDto {
    private String movieName;
    private double duration;
    private double rating;
    private Date releaseDate;
    private Genre genre;
    private Language language;

}
