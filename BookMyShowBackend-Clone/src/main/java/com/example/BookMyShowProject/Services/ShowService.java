package com.example.BookMyShowProject.Services;

import com.example.BookMyShowProject.Convertors.ShowConvertor;
import com.example.BookMyShowProject.Models.*;
import com.example.BookMyShowProject.Repositories.MovieRepository;
import com.example.BookMyShowProject.Repositories.ShowRepository;
import com.example.BookMyShowProject.Repositories.ShowSeatsRepository;
import com.example.BookMyShowProject.Repositories.TheatreRepository;
import com.example.BookMyShowProject.RequestDto.GetShowRequestDto;
import com.example.BookMyShowProject.RequestDto.ShowRequestDto;
import com.example.BookMyShowProject.ResposeDto.ShowResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Temporal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShowService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    ShowSeatsRepository showSeatsRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheatreRepository theatreRepository;


    public void createShow(ShowRequestDto showRequestDto)throws Exception{
        Show show= ShowConvertor.convertDtoToEntity(showRequestDto);

        Theatre theatre=theatreRepository.findById(showRequestDto.getTheatreId()).get();
        Movie movie=movieRepository.findByName(showRequestDto.getMovieName());


        show.setMovie(movie);
        show.setTheatre(theatre);

        movie.getShowList().add(show);
        theatre.getShowList().add(show);

        List<ShowSeats> showSeatsList=createShowSeats(theatre.getTheatreSeatsList());

        show.setShowSeatsList(showSeatsList);

        for(ShowSeats showSeats:showSeatsList){
            showSeats.setShow(show);
        }


        movieRepository.save(movie);
        theatreRepository.save(theatre);

      //  showRepository.save(show);

    }

    public List<ShowSeats> createShowSeats(List<TheatreSeats> theatreSeatsList){
        List<ShowSeats> seats=new ArrayList<>();

        for(TheatreSeats theatreSeats:theatreSeatsList){
            ShowSeats showSeats=ShowSeats.builder().seatType(theatreSeats.getSeatType()).
            seatNo(theatreSeats.getSeatNo()).build();
            seats.add(showSeats);
        }

        showSeatsRepository.saveAll(seats);

        return seats;

    }

    public List<ShowResponseDto> getShows(GetShowRequestDto getShowRequestDto){

        LocalDateTime from=getShowRequestDto.getFrom();
        LocalDateTime to=getShowRequestDto.getTo();



        List<Show> showList=showRepository.findAll();



        List<ShowResponseDto> showResponseDtoList=new ArrayList<>();


        for(Show show:showList){
            LocalDateTime showDateTime=LocalDateTime.of(show.getShowDate(),show.getShowTime());
            if(showDateTime.compareTo(from)>=0 && showDateTime.compareTo(to)<=0)
            {
                ShowResponseDto showResponseDto=ShowResponseDto.builder().id(show.getId()).showDate(show.getShowDate())
                        .showTime(show.getShowTime()).movieName(show.getMovie().getName())
                        .theatreId(show.getTheatre().getId()).multiplier(show.getMultiplier()).build();
                showResponseDtoList.add(showResponseDto);
            }
        }

        return showResponseDtoList;
    }
}











