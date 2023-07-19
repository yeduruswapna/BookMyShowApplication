package com.example.BookMyShowApplication.Exceptions;

public class ShowNotFoundException extends RuntimeException{
    public ShowNotFoundException(String message){
        super(message);
    }
}
