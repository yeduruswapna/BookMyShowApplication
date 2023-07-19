package com.example.BookMyShowApplication.Transformers;

import com.example.BookMyShowApplication.Dtos.RequestDtos.TheaterRequestDto;
import com.example.BookMyShowApplication.Models.Theater;
import lombok.Data;

@Data
public class TheaterTransformer {
    public static Theater convertDtoToEntity(TheaterRequestDto theaterRequestDto){
        Theater theaterObj= Theater.builder()
                .location(theaterRequestDto.getLocation())
                .name(theaterRequestDto.getName())
                .build();
        return theaterObj;
    }
}
