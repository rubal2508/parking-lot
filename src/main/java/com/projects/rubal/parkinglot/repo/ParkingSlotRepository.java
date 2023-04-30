package com.projects.rubal.parkinglot.repo;

import com.projects.rubal.parkinglot.model.ParkingSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot,String> {
    List<ParkingSlot> findByAvailability(boolean status);
}
