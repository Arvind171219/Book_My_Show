package com.example.BookMyShowProject.Repositories;

import com.example.BookMyShowProject.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {

     Movie findByName(String name);
}
