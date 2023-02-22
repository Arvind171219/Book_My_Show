package com.example.BookMyShowProject.Services;

import com.example.BookMyShowProject.Convertors.MovieConvertor;
import com.example.BookMyShowProject.Models.Movie;
import com.example.BookMyShowProject.Repositories.MovieRepository;
import com.example.BookMyShowProject.RequestDto.MovieRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;

    public void createMovie(MovieRequestDto movieRequestDto)throws Exception{
        Movie movie= MovieConvertor.covertDtoToEntity(movieRequestDto);
        movieRepository.save(movie);
    }

    public Movie getMovie(String name)throws Exception{
        Movie movie=movieRepository.findByName(name);
        return movie;
    }
}
