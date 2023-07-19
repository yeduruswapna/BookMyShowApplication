package com.example.BookMyShowApplication.Repository;

import com.example.BookMyShowApplication.Models.Ticket;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
