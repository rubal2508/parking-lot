package com.projects.rubal.parkinglot.service;

import com.projects.rubal.parkinglot.model.ParkingSlot;
import com.projects.rubal.parkinglot.model.ParkingTicket;
import com.projects.rubal.parkinglot.repo.ParkingSlotRepository;
import com.projects.rubal.parkinglot.repo.ParkingTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ParkingService {


    @Autowired
    ParkingSlotRepository slotRepo;

    @Autowired
    ParkingTicketRepository ticketRepo;

    @Autowired
    AutomationService automationService;

    @Autowired
    AdminService adminService;


    public ParkingSlot getNextAvailableSlot(){
        List<ParkingSlot> openSlots = slotRepo.findByAvailability(true);
        if((openSlots != null) && (openSlots.size()>0)){
            return openSlots.get(0);
        }
        return null;
    }



    public ParkingTicket generateTicket(){

        // get available slot
        if (automationService.getDisplayBoardStatus()=="FULL") return null;
        ParkingSlot assignedParkingSlot = getNextAvailableSlot();
        assignedParkingSlot.setAvailability(false);
        slotRepo.save(assignedParkingSlot);

        // scans vehicle
        String vehicleNumber = automationService.scanVehicle();

        // generate ticket
        ParkingTicket parkingTicket = new ParkingTicket();
        parkingTicket.setParkingSlotId(assignedParkingSlot.getParkingSlotId());
        parkingTicket.setVehicleNumber(vehicleNumber);
        parkingTicket.setEnterTime(new Date());
        ticketRepo.save(parkingTicket);

        // open Gate
        automationService.openEntryGate();

        // updateDisplay board
        automationService.updateDisplayBoard();

        return parkingTicket;
    }

    public ParkingTicket generateBill(int ticketId){
        ParkingTicket parkingTicket = ticketRepo.findById(ticketId).get();
        parkingTicket.setExitTime(new Date());

        long duration = parkingTicket.getExitTime().getTime() - parkingTicket.getEnterTime().getTime();
        long durationSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);

        parkingTicket.setTotalCharge(durationSeconds * adminService.getParkingCharges());

        ticketRepo.save(parkingTicket);
        automationService.openExitGate();

        ParkingSlot parkingSlot = slotRepo.findById(parkingTicket.getParkingSlotId()).get();
        parkingSlot.setAvailability(true);
        slotRepo.save(parkingSlot);

        automationService.updateDisplayBoard();

        return parkingTicket;
    }

}
