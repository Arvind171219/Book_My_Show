package com.example.BookMyShowProject.Controllers;

import com.example.BookMyShowProject.Repositories.ShowRepository;
import com.example.BookMyShowProject.RequestDto.GetShowRequestDto;
import com.example.BookMyShowProject.RequestDto.ShowRequestDto;
import com.example.BookMyShowProject.ResposeDto.ShowResponseDto;
import com.example.BookMyShowProject.Services.ShowService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/show")
@Slf4j
public class ShowController {

    @Autowired
    ShowService showService;

    @PostMapping("/create")
    public ResponseEntity<String> createShow(@RequestBody ShowRequestDto showRequestDto){
        try{
            showService.createShow(showRequestDto);
        }catch (Exception e){

            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Show Added Successfully",HttpStatus.ACCEPTED);
    }


    @GetMapping("/get_all_show_between_time")
    public ResponseEntity<List<ShowResponseDto>> getShows(@RequestBody GetShowRequestDto getShowRequestDto){
        try{
            List<ShowResponseDto> showResponseDtoList = showService.getShows(getShowRequestDto);
            return new ResponseEntity<>(showResponseDtoList,HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

}
