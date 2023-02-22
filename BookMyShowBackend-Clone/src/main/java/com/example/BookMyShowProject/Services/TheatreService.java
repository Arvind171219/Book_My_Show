package com.example.BookMyShowProject.Services;

import com.example.BookMyShowProject.Convertors.TheatreConvertor;
import com.example.BookMyShowProject.Enums.SeatType;
import com.example.BookMyShowProject.Models.Movie;
import com.example.BookMyShowProject.Models.Show;
import com.example.BookMyShowProject.Models.Theatre;
import com.example.BookMyShowProject.Models.TheatreSeats;
import com.example.BookMyShowProject.Repositories.ShowRepository;
import com.example.BookMyShowProject.Repositories.TheatreRepository;
import com.example.BookMyShowProject.Repositories.TheatreSeatsRepository;
import com.example.BookMyShowProject.RequestDto.TheatreRequestDto;
import com.example.BookMyShowProject.ResposeDto.TheatreResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreService {

    @Autowired
    TheatreRepository theatreRepository;

    @Autowired
    TheatreSeatsRepository theatreSeatsRepository;

    @Autowired
    ShowRepository showRepository;

    public void createTheatre(TheatreRequestDto theatreRequestDto) throws Exception{
        Theatre theatre= TheatreConvertor.convertDtoToEntity(theatreRequestDto);

        List<TheatreSeats> theatreSeatsList=createTheatreSeats();

        theatre.setTheatreSeatsList(theatreSeatsList);

        for(TheatreSeats theatreSeats:theatreSeatsList){
            theatreSeats.setTheatre(theatre);
        }

        theatreRepository.save(theatre);
    }

    public List<TheatreSeats> createTheatreSeats(){

        List<TheatreSeats> seats=new ArrayList<>();

//        TheatreSeats seats1=new TheatreSeats("A1", SeatType.CLASSIC,100);
//        TheatreSeats seats2=new TheatreSeats("A2",SeatType.CLASSIC,100);
//        TheatreSeats seats3=new TheatreSeats("A3",SeatType.CLASSIC,100);
//        TheatreSeats seats4=new TheatreSeats("A4",SeatType.CLASSIC,100);
//        TheatreSeats seats5=new TheatreSeats("A5",SeatType.CLASSIC,100);
//        TheatreSeats seats6=new TheatreSeats("B1",SeatType.PLATINUM,200);
//        TheatreSeats seats7=new TheatreSeats("B2",SeatType.PLATINUM,200);
//        TheatreSeats seats8=new TheatreSeats("B3",SeatType.PLATINUM,200);
//        TheatreSeats seats9=new TheatreSeats("B4",SeatType.PLATINUM,200);
//        TheatreSeats seats10=new TheatreSeats("B5",SeatType.PLATINUM,200);
//
//        seats.add(seats1);
//        seats.add(seats2);
//        seats.add(seats3);
//        seats.add(seats4);
//        seats.add(seats5);
//        seats.add(seats6);
//        seats.add(seats7);
//        seats.add(seats8);
//        seats.add(seats9);
//        seats.add(seats10);

        for(int i=0;i<5;i++)
        {
            String s1="1"+(char)('A'+i);
            String s2="2"+(char)('A'+i);

            TheatreSeats seats1=new TheatreSeats(s1, SeatType.CLASSIC,100);
            TheatreSeats seats2=new TheatreSeats(s2,SeatType.PLATINUM,200);

            seats.add(seats1);
            seats.add(seats2);

        }
        theatreSeatsRepository.saveAll(seats);
        return seats;
    }

    public TheatreResponseDto getTheatreById(int theatreId)throws Exception{
        if(theatreRepository.findById(theatreId).isPresent()){
            Theatre theatre=theatreRepository.findById(theatreId).get();
            return TheatreResponseDto.builder().id(theatre.getId()).address(theatre.getAddress())
                    .city(theatre.getCity()).name(theatre.getName()).build();

        }
        else throw new Exception("Theatre Not Found");
    }


    public List<TheatreResponseDto> getAllTheatre() throws Exception{
        List<Theatre> theatreList=theatreRepository.findAll();
        List<TheatreResponseDto> theatreResponseDtoList=new ArrayList<>();
        for(Theatre theatre:theatreList){
            TheatreResponseDto theatreResponseDto=TheatreResponseDto.builder().id(theatre.getId())
                    .name(theatre.getName()).city(theatre.getCity()).address(theatre.getAddress()).build();
            theatreResponseDtoList.add(theatreResponseDto);
        }
        return theatreResponseDtoList;
    }


    public List<TheatreResponseDto> getAllTheatreByMovie(String movieName){
        List<Show> showList=showRepository.findAll();
        List<TheatreResponseDto> theatreResponseDtoList=new ArrayList<>();
        for(Show show:showList){

            if(show.getMovie().getName().equals(movieName)){
                Theatre theatre=show.getTheatre();
                TheatreResponseDto theatreResponseDto=TheatreResponseDto.builder().id(theatre.getId())
                        .name(theatre.getName()).city(theatre.getCity()).address(theatre.getAddress()).build();

                if(!theatreResponseDtoList.contains(theatreResponseDto)){
                    theatreResponseDtoList.add(theatreResponseDto);
                }
            }

        }
        return theatreResponseDtoList;
    }




}












