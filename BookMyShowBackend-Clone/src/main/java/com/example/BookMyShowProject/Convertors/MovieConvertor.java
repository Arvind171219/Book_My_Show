package com.example.BookMyShowProject.Convertors;

import com.example.BookMyShowProject.Models.Movie;
import com.example.BookMyShowProject.RequestDto.MovieRequestDto;

public class MovieConvertor {
    public static Movie covertDtoToEntity(MovieRequestDto movieRequestDto){
        Movie movie=Movie.builder().name(movieRequestDto.getName()).
                duration(movieRequestDto.getDuration()).releaseDate(movieRequestDto.getReleaseDate()).build();
        return movie;
    }
}
