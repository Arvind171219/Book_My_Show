package com.example.BookMyShowProject.Services;

import com.example.BookMyShowProject.Models.Show;
import com.example.BookMyShowProject.Models.ShowSeats;
import com.example.BookMyShowProject.Models.Ticket;
import com.example.BookMyShowProject.Models.User;
import com.example.BookMyShowProject.Repositories.ShowRepository;
import com.example.BookMyShowProject.Repositories.TicketRepository;
import com.example.BookMyShowProject.Repositories.UserRepository;
import com.example.BookMyShowProject.RequestDto.TicketRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    ShowRepository showRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;

    public void bookTicket(TicketRequestDto ticketRequestDto)throws Exception{

        Show show=showRepository.findById(ticketRequestDto.getShowId()).get();
        User user=userRepository.findById(ticketRequestDto.getUserId()).get();

        List<String> requestSeats=ticketRequestDto.getAllottedSeats();

        List<ShowSeats> showSeats=show.getShowSeatsList();

        List<ShowSeats> bookedSeats=new ArrayList<>();

        for(ShowSeats seats:showSeats){
            String seatNo= seats.getSeatNo();
            if(!seats.isBooked() && requestSeats.contains(seatNo))
            {
                bookedSeats.add(seats);
            }
        }

        if(bookedSeats.size()!=requestSeats.size()){
            throw  new Exception("Seats Not Available");
        }


        Ticket ticket=new Ticket();


        String allottedSeats="";
        int rate=0;
        double amount=0;

        for(ShowSeats seats:bookedSeats){
            String seatNo= seats.getSeatNo();
            allottedSeats=allottedSeats+seatNo+",";

            if(seatNo.charAt(0)=='1')
            {
                rate=100;
            }
            else rate=200;

            amount+=rate*show.getMultiplier();

            seats.setBooked(true);
            seats.setBookedAt(new Date());
            seats.setTicket(ticket);
            seats.setShow(show);
        }

        ticket.setShowSeatsList(bookedSeats);
        ticket.setBookedAt(new Date());
        ticket.setShow(show);
        ticket.setAllottedSeats(allottedSeats);
        ticket.setAmount((int)amount);
        ticket.setUser(user);



        //Bidirectional Relations
//       show.getTicketList().add(ticket);
//       user.getTicketList().add(ticket);



        //  showRepository.save(show);
        //   userRepository.save(user);


            ticketRepository.save(ticket);




    }


    public int cancelTicket(int ticketId)throws Exception{
        Ticket ticket=ticketRepository.findById(ticketId).get();

        String allottedSeats=ticket.getAllottedSeats();
        int refund=ticket.getAmount();

        String[] seats=allottedSeats.split(",");

        List<String> seatList=new ArrayList<>(List.of(seats));

        Show show=ticket.getShow();

        List<ShowSeats> showSeats=show.getShowSeatsList();

        for(ShowSeats seats1:showSeats){
            if(seatList.contains(seats1.getSeatNo())){
                seats1.setBookedAt(null);
                seats1.setBooked(false);
                seats1.setTicket(null);
            }
        }

        ticketRepository.delete(ticket);

        return refund;


    }
}
