package com.example.BookMyShowProject.Repositories;

import com.example.BookMyShowProject.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
