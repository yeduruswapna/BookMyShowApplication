package com.example.BookMyShowApplication.Exceptions;

import com.example.BookMyShowApplication.Services.TheaterService;

public class TheaterNotFoundException extends RuntimeException{
    public TheaterNotFoundException(String message){
        super(message);
    }
}
