package com.example.BookMyShowApplication.Transformers;

import com.example.BookMyShowApplication.Dtos.RequestDtos.UserEntryDto;
import com.example.BookMyShowApplication.Dtos.ResponseDtos.UserResponseDto;
import com.example.BookMyShowApplication.Models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

public class UserTransformer {
    public static User convertDtoToEntity(UserEntryDto userEntryDto){
        User user= User.builder()
                .name(userEntryDto.getName())
                .emailId(userEntryDto.getEmailId())
                .age(userEntryDto.getAge())
                .mobileNo(userEntryDto.getMobileNo())
                .build();
        return user;
    }
    public static UserResponseDto convertEntityToDto(User user) {
        UserResponseDto userResponseDto = UserResponseDto.builder()
                .name(user.getName())
                .age(user.getAge())
                .mobileNo(user.getMobileNo())
                .build();
        return userResponseDto;
    }
}
