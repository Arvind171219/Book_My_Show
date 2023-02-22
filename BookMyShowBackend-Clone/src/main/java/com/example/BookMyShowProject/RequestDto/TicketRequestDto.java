package com.example.BookMyShowProject.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class TicketRequestDto {

    private List<String> allottedSeats;

    private int showId;

    private int userId;
}
