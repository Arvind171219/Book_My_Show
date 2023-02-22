package com.example.BookMyShowProject.Services;

import com.example.BookMyShowProject.Convertors.UserConvertor;
import com.example.BookMyShowProject.Models.Show;
import com.example.BookMyShowProject.Models.Ticket;
import com.example.BookMyShowProject.Models.User;
import com.example.BookMyShowProject.Repositories.TicketRepository;
import com.example.BookMyShowProject.Repositories.UserRepository;
import com.example.BookMyShowProject.RequestDto.UserRequestDto;
import com.example.BookMyShowProject.ResposeDto.UserBookedTicketsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;



    public void createUser(UserRequestDto userRequestDto)throws Exception{
        User user= UserConvertor.convertDtoToEntity(userRequestDto);
        userRepository.save(user);
    }

    public List<UserBookedTicketsDto> getUserBookedTickets(int userId){
        List<Ticket> ticketList=ticketRepository.findAll();
        List<UserBookedTicketsDto> userBookedTicketsDtoList=new ArrayList<>();
        for(Ticket ticket:ticketList){
            if(ticket.getUser().getId()==userId){
                Show show=ticket.getShow();

                UserBookedTicketsDto userBookedTicketsDto=new UserBookedTicketsDto();

                userBookedTicketsDto.setTicketId(ticket.getId());
                userBookedTicketsDto.setAllottedSeats(ticket.getAllottedSeats());
                userBookedTicketsDto.setAmount(ticket.getAmount());
                userBookedTicketsDto.setShowId(show.getId());
                userBookedTicketsDto.setMovieName(show.getMovie().getName());
                userBookedTicketsDto.setTheatreName(show.getTheatre().getName());
                userBookedTicketsDto.setShowDate(show.getShowDate());
                userBookedTicketsDto.setShowTime(show.getShowTime());

                userBookedTicketsDtoList.add(userBookedTicketsDto);
            }
        }
        return userBookedTicketsDtoList;

    }
}














