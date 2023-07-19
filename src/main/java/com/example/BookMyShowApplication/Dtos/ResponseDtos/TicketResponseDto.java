package com.example.BookMyShowApplication.Dtos.ResponseDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDto {
    private String responseMessage;
    private String theaterName;
    private String location;
    private String movieName;
    private Date showDate;
    private LocalTime showTime;
    private String bookedSeats;
    private int totalPrice;
}
