package com.projects.rubal.parkinglot.repo;

import com.projects.rubal.parkinglot.model.ParkingTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingTicketRepository extends JpaRepository<ParkingTicket,Integer> {
}
