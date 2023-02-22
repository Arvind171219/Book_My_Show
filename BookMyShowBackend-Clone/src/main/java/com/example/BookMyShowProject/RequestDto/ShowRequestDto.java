package com.example.BookMyShowProject.RequestDto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ShowRequestDto {

    private LocalDate showDate;

    private LocalTime showTime;

    private Double multiplier;

    private String movieName;

    private int theatreId;

}
