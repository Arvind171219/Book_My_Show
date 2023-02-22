package com.example.BookMyShowProject.RequestDto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class GetShowRequestDto {

    private LocalDateTime from;

    private LocalDateTime to;
}
