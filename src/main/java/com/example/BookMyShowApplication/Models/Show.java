package com.example.BookMyShowApplication.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name= "shows")
@Entity
@Data

@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalTime showTime;
    private Date showDate;

    @ManyToOne
    @JoinColumn
    private Movie movie;

    @ManyToOne
    @JoinColumn
    private Theater theater;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    List<ShowSeat> showSeatList= new ArrayList<>();

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    List<Ticket> ticketList= new ArrayList<>();


}
