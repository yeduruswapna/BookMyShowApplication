package com.example.BookMyShowApplication.Controllers;

import com.example.BookMyShowApplication.Dtos.RequestDtos.ShowEntryDto;
import com.example.BookMyShowApplication.Dtos.RequestDtos.ShowSeatsDto;
import com.example.BookMyShowApplication.Dtos.RequestDtos.ShowTimingsDto;
import com.example.BookMyShowApplication.Services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/show")
public class ShowController {
    @Autowired
    ShowService showService;
    @PostMapping("/add")
    public String addShow(@RequestBody ShowEntryDto showEntryDto){
        try{
            return showService.addShow(showEntryDto);
        }catch(Exception e){
            return e.getMessage();
        }
    }
    @PostMapping("/associateSeats")
    public String associateSeats(@RequestBody ShowSeatsDto showSeatsDto){
        try{
            return showService.associateSeats(showSeatsDto);
        }catch(Exception e){
            return e.getMessage();
        }
    }
    @GetMapping("/showTimingsOnDate")
    public ResponseEntity<List<Time>> showTimingsOnDate(@RequestBody ShowTimingsDto showTimingsDto) {
        try {
            List<Time> result = showService.showTimingsOnDate(showTimingsDto);
            return new ResponseEntity<>(result, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/movieHavingMostShows")
    public ResponseEntity<String> movieHavingMostShows() {
        try {
            String movie = showService.movieHavingMostShows();
            return new ResponseEntity<>(movie, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
