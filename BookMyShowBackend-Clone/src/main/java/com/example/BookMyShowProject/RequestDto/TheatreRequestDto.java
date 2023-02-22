package com.example.BookMyShowProject.RequestDto;

import javax.persistence.Column;
import lombok.Data;

@Data
public class TheatreRequestDto {

    @Column(nullable = false)
    private String name;

    private String city;

    private String address;

}
