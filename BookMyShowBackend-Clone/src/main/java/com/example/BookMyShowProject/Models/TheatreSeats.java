package com.example.BookMyShowProject.Models;

import com.example.BookMyShowProject.Enums.SeatType;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Theatre_seats")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheatreSeats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private String seatNo;

   @Enumerated(EnumType.STRING)
    private SeatType seatType;

    private int rate;

    @ManyToOne
    @JoinColumn
    private Theatre theatre;


    public TheatreSeats(String seatNo, SeatType seatType, int rate) {
        this.seatNo = seatNo;
        this.seatType = seatType;
        this.rate = rate;
    }
}
