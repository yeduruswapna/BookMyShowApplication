package com.example.BookMyShowApplication.Services;

import com.example.BookMyShowApplication.Dtos.RequestDtos.TheaterRequestDto;
import com.example.BookMyShowApplication.Dtos.RequestDtos.TheaterSeatsEntryDto;
import com.example.BookMyShowApplication.Enums.SeatType;
import com.example.BookMyShowApplication.Exceptions.TheaterExistsAlready;
import com.example.BookMyShowApplication.Exceptions.TheaterNotFoundException;
import com.example.BookMyShowApplication.Models.Theater;
import com.example.BookMyShowApplication.Models.TheaterSeat;
import com.example.BookMyShowApplication.Repository.TheaterRepository;
import com.example.BookMyShowApplication.Transformers.TheaterTransformer;
import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterService {
    @Autowired
    TheaterRepository theaterRepository;

    public String addUser(TheaterRequestDto theaterRequestDto)throws TheaterExistsAlready{
        if(theaterRepository.findByLocation(theaterRequestDto.getLocation())!=null)
            throw new TheaterExistsAlready("Theater Exists already ");

        Theater theater= TheaterTransformer.convertDtoToEntity(theaterRequestDto);
        theaterRepository.save(theater);
        return "Theater Added Successfully";

    }
    public String addTheaterSeats(TheaterSeatsEntryDto theaterSeatsEntryDto) {
        int columns= theaterSeatsEntryDto.getNoOfSeatsInRow();
        int noOfClassicSeats=theaterSeatsEntryDto.getNoOfClassicSeats();
        int noOfPremiumSeats=theaterSeatsEntryDto.getNoOfPremiumSeats();
        String location= theaterSeatsEntryDto.getLocation();

        Theater theater= theaterRepository.findByLocation(location);

        List<TheaterSeat> theaterSeatList= theater.getTheaterSeatList();

        //for classic seats
        int counter=1;
        char ch= 'A';
        for(int count=1; count<=noOfClassicSeats; count++){
            String seatNo= counter + "";
            seatNo= seatNo + ch;
            ch++;
            if((ch-'A')==columns){
                ch='A';
                counter++;
            }
            TheaterSeat theaterSeat= new TheaterSeat();
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setTheater(theater);
            theaterSeat.setSeatType(SeatType.CLASSIC);
            theaterSeatList.add(theaterSeat);
        }
        //for premium seats
        for(int count=1; count<=noOfPremiumSeats; count++) {
            String seatNo = counter + "";
            seatNo = seatNo + ch;
            ch++;
            if((ch - 'A')== columns){
                ch='A';
                counter++;
            }
            TheaterSeat theaterSeat = new TheaterSeat();
            theaterSeat.setTheater(theater);
            theaterSeat.setSeatNo(seatNo);
            theaterSeat.setSeatType(SeatType.PREMIUM);
            theaterSeatList.add(theaterSeat);
        }
        theaterRepository.save(theater);
        return "TheaterSeats Have Been Added Successfully";
    }
}






















