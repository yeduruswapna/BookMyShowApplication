package com.example.BookMyShowApplication.Transformers;

import com.example.BookMyShowApplication.Dtos.RequestDtos.ShowEntryDto;
import com.example.BookMyShowApplication.Models.Show;
import lombok.Data;

@Data
public class ShowTransformer {
    public static Show convertDtoToEntity(ShowEntryDto showEntryDto){
        Show show= Show.builder()
                .showDate(showEntryDto.getShowDate())
                .showTime(showEntryDto.getShowTime())
                .build();
        return show;
    }
}
