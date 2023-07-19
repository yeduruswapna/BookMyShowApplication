package com.example.BookMyShowApplication.Services;

import com.example.BookMyShowApplication.Dtos.RequestDtos.ShowEntryDto;
import com.example.BookMyShowApplication.Dtos.RequestDtos.ShowSeatsDto;
import com.example.BookMyShowApplication.Dtos.RequestDtos.ShowTimingsDto;
import com.example.BookMyShowApplication.Enums.SeatType;
import com.example.BookMyShowApplication.Exceptions.MovieNotFoundException;
import com.example.BookMyShowApplication.Exceptions.ShowNotFoundException;
import com.example.BookMyShowApplication.Exceptions.TheaterNotFoundException;
import com.example.BookMyShowApplication.Models.*;
import com.example.BookMyShowApplication.Repository.MovieRepository;
import com.example.BookMyShowApplication.Repository.ShowRepository;
import com.example.BookMyShowApplication.Repository.TheaterRepository;
import com.example.BookMyShowApplication.Transformers.ShowTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
    @Autowired
    ShowRepository showRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TheaterRepository theaterRepository;
    public String addShow(ShowEntryDto showEntryDto) throws MovieNotFoundException, TheaterNotFoundException {
        Show show= ShowTransformer.convertDtoToEntity(showEntryDto);

        Optional<Movie> movieOptional= movieRepository.findById(showEntryDto.getMovieId());
        if(!movieOptional.isPresent())
            throw  new MovieNotFoundException("Movie Not Found");

        Optional<Theater> theaterOptional= theaterRepository.findById(showEntryDto.getTheaterId());
        if(!theaterOptional.isPresent())
            throw new TheaterNotFoundException("Theater Not Found");

        Movie movie=movieOptional.get();
        Theater theater= theaterOptional.get();

        show.setMovie(movie);
        show.setTheater(theater);
        show=showRepository.save(show);

        movie.getShowList().add(show);
        movieRepository.save(movie);

        theater.getShowList().add(show);
        theaterRepository.save(theater);

        return "Show Has Been Added And Show Id Is " + show.getId();
    }

    public String associateSeats(ShowSeatsDto showSeatsDto) throws ShowNotFoundException {
        Optional<Show> showOptional= showRepository.findById(showSeatsDto.getShowId());

        //validate
        if(!showOptional.isPresent())
            throw new ShowNotFoundException("Show Id Incorrect");

        //valid show now
        Show show= showOptional.get();

        //Get the theaterlist and  showseatlist and associate them
        Theater theater= show.getTheater();
        List<TheaterSeat> theaterSeatList= theater.getTheaterSeatList();

        List<ShowSeat>showSeatList= show.getShowSeatList();

        for(TheaterSeat theaterSeat: theaterSeatList){
            ShowSeat showSeat= new ShowSeat();
            showSeat.setSeatNo(theaterSeat.getSeatNo());
            showSeat.setSeatType(theaterSeat.getSeatType());

            if(showSeat.getSeatType().equals(SeatType.CLASSIC))
                showSeat.setPrice(showSeatsDto.getPriceForClassicSeats());
            else
                showSeat.setPrice((showSeatsDto.getPriceForPremiumSeats()));

            //foreignkey mapping
           showSeat.setShow(show);
           showSeat.setAvailable(true);
           showSeat.setFoodAttached(false);

            showSeatList.add(showSeat);
        }
        //save parent
        showRepository.save(show);
        return "Show Seats Have been added Successfully";

    }

    public List<Time> showTimingsOnDate(ShowTimingsDto showTimingsDto){
        String date = showTimingsDto.getShowDate();
        Integer theaterId = showTimingsDto.getTheaterId();
        Integer movieId = showTimingsDto.getMovieId();
        return showRepository.getShowTimingsOnDate(date, theaterId, movieId);
    }

    public String movieHavingMostShows() {
        Integer movieId = showRepository.getMostShowsMovie();
        return movieRepository.findById(movieId).get().getMovieName();
    }
}














