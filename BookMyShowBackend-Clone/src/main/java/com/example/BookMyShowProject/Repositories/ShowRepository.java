package com.example.BookMyShowProject.Repositories;

import com.example.BookMyShowProject.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show,Integer> {
}
