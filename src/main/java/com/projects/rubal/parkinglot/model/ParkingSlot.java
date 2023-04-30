package com.projects.rubal.parkinglot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSlot {
    @Id
    private String parkingSlotId;
    private int floor;
    private boolean availability;
}
