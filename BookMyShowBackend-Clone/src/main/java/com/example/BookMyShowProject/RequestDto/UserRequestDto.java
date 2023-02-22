package com.example.BookMyShowProject.RequestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserRequestDto {

    private String name;

    private String mobileNo;
}

