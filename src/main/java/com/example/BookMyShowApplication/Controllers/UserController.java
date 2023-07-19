package com.example.BookMyShowApplication.Controllers;

import com.example.BookMyShowApplication.Dtos.RequestDtos.UserEntryDto;
import com.example.BookMyShowApplication.Dtos.ResponseDtos.UserResponseDto;
import com.example.BookMyShowApplication.Exceptions.NoUserFoundException;
import com.example.BookMyShowApplication.Models.User;
import com.example.BookMyShowApplication.Services.UserService;
import com.example.BookMyShowApplication.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserEntryDto userEntryDto){
        try{
            String str= userService.addUser(userEntryDto);
            return new ResponseEntity<>(str, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    //Get oldest user object by age
    @GetMapping("/getOldestUser")
    public UserResponseDto getOldestUser(){
        try{
            UserResponseDto userResponseDto= userService.getOldestUser();
            userResponseDto.setStatusMessage("Success");
            userResponseDto.setStatusCode("200");
            return userResponseDto;
        }catch(Exception e){
            UserResponseDto userResponseDto= new UserResponseDto();
            userResponseDto.setStatusCode("500");
            userResponseDto.setStatusMessage("Failure");
            return userResponseDto;
        }
    }
    @GetMapping("/findUsersGreaterThanAge")
    public List<User> findUsersGreaterThanAge(@RequestParam("age") Integer age){
        return userService.findUsersGreaterThanAge(age);
    }
}



















