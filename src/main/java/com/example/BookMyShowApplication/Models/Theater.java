package com.example.BookMyShowApplication.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name="theater")
@Entity
@Data

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @Column(unique = true)
    private String location;

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    List<TheaterSeat> theaterSeatList= new ArrayList<>();

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    List<Show> showList= new ArrayList<>();
}
