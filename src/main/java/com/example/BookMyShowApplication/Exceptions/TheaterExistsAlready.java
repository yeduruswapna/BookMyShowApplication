package com.example.BookMyShowApplication.Exceptions;

public class TheaterExistsAlready extends RuntimeException{
    public TheaterExistsAlready(String message){
        super(message);
    }
}
