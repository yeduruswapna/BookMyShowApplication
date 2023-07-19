package com.example.BookMyShowApplication.Dtos.RequestDtos;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

import java.time.LocalTime;
import java.util.Date;

@Data
public class ShowTimingsDto {
    private Integer theaterId;
    private Integer movieId;
    private String showDate;
}
