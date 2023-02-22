package com.example.BookMyShowProject.Models;

import com.example.BookMyShowProject.Enums.SeatType;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "Show_seats")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShowSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String seatNo;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    private Date bookedAt;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean booked;

    @ManyToOne
    @JoinColumn
    private Show show;

    @ManyToOne
    @JoinColumn
    private Ticket ticket;

}
