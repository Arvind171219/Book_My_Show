package com.example.BookMyShowProject.Controllers;

import com.example.BookMyShowProject.Models.Theatre;
import com.example.BookMyShowProject.RequestDto.TheatreRequestDto;
import com.example.BookMyShowProject.ResposeDto.TheatreResponseDto;
import com.example.BookMyShowProject.Services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

    @Autowired
    TheatreService theatreService;

    @PostMapping("/create")
    public ResponseEntity<String> createTheatre(@RequestBody TheatreRequestDto theatreRequestDto){
        try{
            theatreService.createTheatre(theatreRequestDto);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Theatre Added Successfully", HttpStatus.ACCEPTED);
    }

    @GetMapping("get_by_id")
    public ResponseEntity<TheatreResponseDto> getTheatreById(@RequestParam int theatreId){
        try{
            TheatreResponseDto theatre=theatreService.getTheatreById(theatreId);
            return new ResponseEntity<>(theatre,HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get_all_theatre")
    public ResponseEntity<List<TheatreResponseDto>> getAllTheatre(){
        try {
            return new ResponseEntity<>(theatreService.getAllTheatre(),HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);

        }
    }

    @GetMapping("/get_all_theatres_by_movie_name")
    public ResponseEntity<List<TheatreResponseDto>> getAllTheatreByMovie(@RequestParam String movieName){
        try{
            List<TheatreResponseDto> theatreResponseDtoList=theatreService.getAllTheatreByMovie(movieName);
            return new ResponseEntity<>(theatreResponseDtoList,HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }


}
