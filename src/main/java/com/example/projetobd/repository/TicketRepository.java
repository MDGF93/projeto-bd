package com.example.projetobd.repository;

import com.example.projetobd.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Integer countBySessionId(Long sessionId);
}
