package com.example.BookMyShowApplication.Exceptions;

public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(String message){

        super(message);
    }
}
