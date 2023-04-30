package com.projects.rubal.parkinglot.controller;

import com.projects.rubal.parkinglot.model.AdminUser;
import com.projects.rubal.parkinglot.model.ParkingSlot;
import com.projects.rubal.parkinglot.model.ParkingTicket;
import com.projects.rubal.parkinglot.service.AdminService;
import com.projects.rubal.parkinglot.service.AutomationService;
import com.projects.rubal.parkinglot.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ParkingController {

    @Autowired
    AdminService adminService;

    @Autowired
    AutomationService automationService;

    @Autowired
    ParkingService parkingService;


    @PostMapping("/addAdmin")
    public AdminUser addAdmin(@RequestBody AdminUser adminUser){
        return adminService.addAdminUser(adminUser.getId(), adminUser.getPassword());
    }

    @PostMapping("/updateParkingCharge")
    public boolean updateParkingCharge(@RequestBody AdminUser adminUser, @RequestParam Double parkingCharge){
        return adminService.updateParkingCharge(parkingCharge, adminUser);
    }

    @PostMapping("/addParkingSlots")
    public List<ParkingSlot> addParkingSlots(@RequestBody List<ParkingSlot> newParkingSlots){
        return adminService.updateOrAddParkingSlots(newParkingSlots);
    }

    @GetMapping("getParkingCharges")
    public Double getParkingCharges(){
        return adminService.getParkingCharges();
    }

    @GetMapping("getDisplayBoard")
    public String getDisplayBoardStatus(){
        return automationService.getDisplayBoardStatus();
    }

    @PostMapping("/generateTicket")
    public ParkingTicket generateTicket(){
        return parkingService.generateTicket();
    }

    @PostMapping("/generateBill")
    public ParkingTicket generateBill(@RequestParam int ticketId){
        return parkingService.generateBill(ticketId);
    }
}



/*
    SELECT * from ADMIN_USER ;
    SELECT * FROM PARKING_SLOT ;
    SELECT * FROM PARKING_TICKET ;
 */