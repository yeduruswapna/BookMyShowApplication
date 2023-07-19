package com.example.BookMyShowApplication.Controllers;

import com.example.BookMyShowApplication.Dtos.RequestDtos.TheaterRequestDto;
import com.example.BookMyShowApplication.Dtos.RequestDtos.TheaterSeatsEntryDto;
import com.example.BookMyShowApplication.Services.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/theater")
public class TheaterController {
    @Autowired
    TheaterService theaterService;
    @PostMapping("/add")
    public ResponseEntity<String> addTheater(@RequestBody TheaterRequestDto theaterRequestDto){
        try{
            String str= theaterService.addUser(theaterRequestDto);
            return new ResponseEntity<>(str, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/addTheaterSeats")
    public String addTheaterSeats(@RequestBody TheaterSeatsEntryDto theaterSeatsEntryDto){
        return theaterService.addTheaterSeats(theaterSeatsEntryDto);
        }
}
















