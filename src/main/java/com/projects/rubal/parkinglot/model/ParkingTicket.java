package com.projects.rubal.parkinglot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkingTicket {
    @Id
    @GeneratedValue
    private int ticketId;
    private String parkingSlotId;
    private String vehicleNumber;
    private Date enterTime;
    private Date exitTime;
    private Double totalCharge;
}
