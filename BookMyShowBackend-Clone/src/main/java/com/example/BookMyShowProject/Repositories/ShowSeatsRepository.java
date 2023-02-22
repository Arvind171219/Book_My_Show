package com.example.BookMyShowProject.Repositories;

import com.example.BookMyShowProject.Models.ShowSeats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowSeatsRepository extends JpaRepository<ShowSeats,Integer> {
}
