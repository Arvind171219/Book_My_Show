package com.example.BookMyShowProject.Convertors;

import com.example.BookMyShowProject.Models.User;
import com.example.BookMyShowProject.RequestDto.UserRequestDto;

public class UserConvertor {
    public static User convertDtoToEntity(UserRequestDto userRequestDto){
        User user=User.builder().name(userRequestDto.getName()).mobileNo(userRequestDto.getMobileNo()).build();
        return user;
    }
}
