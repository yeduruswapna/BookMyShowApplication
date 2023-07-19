package com.example.BookMyShowApplication.Services;

import com.example.BookMyShowApplication.Dtos.RequestDtos.TicketRequestDto;
import com.example.BookMyShowApplication.Dtos.ResponseDtos.TicketResponseDto;
import com.example.BookMyShowApplication.Exceptions.NoUserFoundException;
import com.example.BookMyShowApplication.Exceptions.ShowNotFoundException;
import com.example.BookMyShowApplication.Models.Show;
import com.example.BookMyShowApplication.Models.ShowSeat;
import com.example.BookMyShowApplication.Models.Ticket;
import com.example.BookMyShowApplication.Models.User;
import com.example.BookMyShowApplication.Repository.ShowRepository;
import com.example.BookMyShowApplication.Repository.TicketRepository;
import com.example.BookMyShowApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ShowRepository showRepository;

    @Autowired
    JavaMailSender emailSender;
    public TicketResponseDto bookTicket(TicketRequestDto ticketRequestDto) throws NoUserFoundException, ShowNotFoundException {
        Optional<User> userOptional= userRepository.findById(ticketRequestDto.getUserId());
        if(!userOptional.isPresent())
            throw new NoUserFoundException("User Id Is Incorrect");

        Optional<Show> showOptional= showRepository.findById(ticketRequestDto.getShowId());
        if(!showOptional.isPresent())
            throw new ShowNotFoundException("Show Id Is Incorrect");

        Show show= showOptional.get();

        //validate whether requested seats are available or not
        boolean isValid= validateShowAvailability(show, ticketRequestDto.getRequestedSeats());
        if(isValid==false)
            throw new RuntimeException("Requested Seats Are Unavailable");

        //calculate ticket price
        Ticket ticket= new Ticket();

        int totalPrice=calculateTotalPrice(show, ticketRequestDto.getRequestedSeats());
        ticket.setTotalTicketPrice(totalPrice);

        String bookedSeats=convertListToString(ticketRequestDto.getRequestedSeats());
        ticket.setBookedSeats(bookedSeats);

        //bidirectional Mapping
        User user=userOptional.get();

        ticket.setUser(user);
        ticket.setShow(show);

        ticket=ticketRepository.save(ticket);

        user.getTicketList().add(ticket);
        userRepository.save(user);

        show.getTicketList().add(ticket);
        showRepository.save(show);

        //We can send a mail to the person
        SimpleMailMessage simpleMessageMail = new SimpleMailMessage();

        String body = "Hi "+user.getName()+" ! \n"+
                "You have successfully booked a ticket. Please find the following details: "+ "\n" +
                "Booked seat No's: "  + bookedSeats + "\n" +
                "Movie Name: " + show.getMovie().getMovieName()+"\n" +
                "Show Date is "+show.getShowDate()+"\n" +
                "Show time is "+show.getShowTime()+"\n" +
                "Enjoy the Show !!!";

        simpleMessageMail.setSubject("Ticket Confirmation Mail");
        simpleMessageMail.setFrom("yeduruswapnareddy@gmail.com");
        simpleMessageMail.setText(body);
        simpleMessageMail.setTo(user.getEmailId());

        emailSender.send(simpleMessageMail);



        return createTicketResponseDto(show, ticket);
    }

    private boolean validateShowAvailability(Show show, List<String> requestedSeats){
        List<ShowSeat> showSeatList= show.getShowSeatList();
        for(ShowSeat showSeat: showSeatList){
            String seatNo=showSeat.getSeatNo();
            if(requestedSeats.contains(seatNo)){
                if(showSeat.isAvailable()==false){
                    return false;
                }
            }
        }
        return true;
    }

    private int calculateTotalPrice(Show show, List<String> requestedSeats) {
        int totalPrice=0;
        List<ShowSeat>showSeatList= show.getShowSeatList();
        for(ShowSeat showSeat: showSeatList){
            if(requestedSeats.contains(showSeat.getSeatNo())){
                totalPrice= totalPrice+ showSeat.getPrice();
                showSeat.setAvailable(false);
            }
        }
        return totalPrice;
    }


    private String convertListToString(List<String> requestedSeats) {
        String result="";
        for(String seatNo: requestedSeats){
            result= result + seatNo +" ";
        }
        return result;
    }

    private TicketResponseDto createTicketResponseDto(Show show, Ticket ticket) {
        TicketResponseDto ticketResponseDto=TicketResponseDto
                .builder()
                .bookedSeats(ticket.getBookedSeats())
                .showTime(show.getShowTime())
                .showDate(show.getShowDate())
                .totalPrice(ticket.getTotalTicketPrice())
                .movieName(show.getMovie().getMovieName())
                .theaterName(show.getTheater().getName())
                .location(show.getTheater().getLocation())
                .build();
        return ticketResponseDto;
    }

}



















