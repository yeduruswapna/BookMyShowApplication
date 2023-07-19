package com.example.BookMyShowApplication.Services;

import com.example.BookMyShowApplication.Dtos.RequestDtos.UserEntryDto;
import com.example.BookMyShowApplication.Dtos.ResponseDtos.UserResponseDto;
import com.example.BookMyShowApplication.Exceptions.NoUserFoundException;
import com.example.BookMyShowApplication.Exceptions.UserAlreadyExistsWithEmail;
import com.example.BookMyShowApplication.Models.User;
import com.example.BookMyShowApplication.Repository.UserRepository;
import com.example.BookMyShowApplication.Transformers.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public String addUser(UserEntryDto userEntryDto)throws UserAlreadyExistsWithEmail{
       if(userRepository.findByEmailId(userEntryDto.getEmailId())!=null)
            throw new UserAlreadyExistsWithEmail("User With This Email Exists Already");

        User user= UserTransformer.convertDtoToEntity(userEntryDto);
        userRepository.save(user);
        return "User Saved Successfully";
    }


    public UserResponseDto getOldestUser() {
        List<User> users= userRepository.findAll();
        int maxAge=0;
        User userAns=null;
        for(User user: users){
            if(user.getAge()>maxAge){
                maxAge= user.getAge();
                userAns=user;
            }
        }
        if(userAns==null){
            throw new NoUserFoundException("No User Found");
        }
        UserResponseDto userResponseDto= UserTransformer.convertEntityToDto(userAns);
        return userResponseDto;
    }

    public List<User> findUsersGreaterThanAge(Integer age) {
        List<User> users= userRepository.findUserGreaterThanAge(age);
        return users;
    }
}


































