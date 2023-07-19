package com.example.BookMyShowApplication.Models;

import com.example.BookMyShowApplication.Enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name= "showseats")
@Entity
@Data

public class ShowSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String seatNo;
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
    private int price;
    boolean isAvailable;
    boolean isFoodAttached;

    @ManyToOne
    @JoinColumn
    private Show show;
}
