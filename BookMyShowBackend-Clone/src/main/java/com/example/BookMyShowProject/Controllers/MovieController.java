package com.example.BookMyShowProject.Controllers;

import com.example.BookMyShowProject.Models.Movie;
import com.example.BookMyShowProject.RequestDto.MovieRequestDto;
import com.example.BookMyShowProject.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping("/create")
    public ResponseEntity<String> createMovie(@RequestBody MovieRequestDto movieRequestDto){
        try{
            movieService.createMovie(movieRequestDto);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Movie Added Successfully",HttpStatus.ACCEPTED);
    }

    @GetMapping("/get_movie")
    public ResponseEntity<Movie> getMovie(@RequestParam()String name){
        try{
            Movie movie=movieService.getMovie(name);
            return new ResponseEntity<>(movie,HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }

    }


}
