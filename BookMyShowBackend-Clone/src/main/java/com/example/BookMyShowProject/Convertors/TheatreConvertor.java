package com.example.BookMyShowProject.Convertors;

import com.example.BookMyShowProject.Models.Theatre;
import com.example.BookMyShowProject.RequestDto.TheatreRequestDto;

public class TheatreConvertor {
    public static Theatre convertDtoToEntity(TheatreRequestDto theatreRequestDto){
        Theatre theatre=Theatre.builder().name(theatreRequestDto.getName()).address(theatreRequestDto.getAddress())
                .city(theatreRequestDto.getCity()).build();

        return theatre;
    }
}
