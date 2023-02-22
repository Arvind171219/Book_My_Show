package com.example.BookMyShowProject.Convertors;

import com.example.BookMyShowProject.Models.Show;
import com.example.BookMyShowProject.RequestDto.ShowRequestDto;

public class ShowConvertor {
    public static Show convertDtoToEntity(ShowRequestDto showRequestDto){
        Show show=Show.builder().showDate(showRequestDto.getShowDate()).showTime(showRequestDto.getShowTime())
                .multiplier(showRequestDto.getMultiplier()).build();
        return show;
    }
}
