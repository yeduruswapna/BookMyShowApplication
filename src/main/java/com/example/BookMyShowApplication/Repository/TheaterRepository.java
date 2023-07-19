package com.example.BookMyShowApplication.Repository;

import com.example.BookMyShowApplication.Models.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater, Integer> {
    Theater findByLocation(String location);

}
