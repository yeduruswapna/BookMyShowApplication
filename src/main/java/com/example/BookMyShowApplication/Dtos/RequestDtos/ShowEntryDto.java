package com.example.BookMyShowApplication.Dtos.RequestDtos;

import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class ShowEntryDto {
    private LocalTime showTime;
    private Date showDate;
    private int movieId;
    private int theaterId;
}
