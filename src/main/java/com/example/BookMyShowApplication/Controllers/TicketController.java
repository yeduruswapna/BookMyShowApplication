package com.example.BookMyShowApplication.Controllers;

import com.example.BookMyShowApplication.Dtos.RequestDtos.TicketRequestDto;
import com.example.BookMyShowApplication.Dtos.ResponseDtos.TicketResponseDto;
import com.example.BookMyShowApplication.Services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @PostMapping("/bookTicket")
    public ResponseEntity<TicketResponseDto> bookTicket(@RequestBody TicketRequestDto ticketRequestDto){
        try{
            TicketResponseDto ticketResponseDto= ticketService.bookTicket(ticketRequestDto);
            ticketResponseDto.setResponseMessage("Ticket Booked Successfully");
            return new ResponseEntity<>(ticketResponseDto, HttpStatus.OK);
        }catch(Exception e){
            TicketResponseDto ticketResponseDto= new TicketResponseDto();
            ticketResponseDto.setResponseMessage(e.getMessage());
            return new ResponseEntity<>(ticketResponseDto, HttpStatus.BAD_REQUEST);
        }
    }
}
