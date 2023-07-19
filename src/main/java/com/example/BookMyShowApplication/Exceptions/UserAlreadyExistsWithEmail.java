package com.example.BookMyShowApplication.Exceptions;

public class UserAlreadyExistsWithEmail extends RuntimeException{
    public UserAlreadyExistsWithEmail(String message){
        super(message);
    }
}
