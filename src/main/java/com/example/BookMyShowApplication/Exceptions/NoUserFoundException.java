package com.example.BookMyShowApplication.Exceptions;

public class NoUserFoundException extends RuntimeException{
    public NoUserFoundException(String message){
        super(message);
    }
}
