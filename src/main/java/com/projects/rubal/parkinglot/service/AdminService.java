package com.projects.rubal.parkinglot.service;

import com.projects.rubal.parkinglot.model.AdminUser;
import com.projects.rubal.parkinglot.model.ParkingSlot;
import com.projects.rubal.parkinglot.repo.AdminUserRepository;
import com.projects.rubal.parkinglot.repo.ParkingSlotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    AdminUserRepository adminRepo;

    @Autowired
    ParkingSlotRepository slotRepo;

    @Autowired
    AutomationService automationService;

    private double parkingChargePerSecond = 0;

    static final Logger log = LoggerFactory.getLogger(AdminService.class);


    public AdminUser addAdminUser(String id, String password){
        //todo: add exception handling
        AdminUser newAdmin = new AdminUser(id, password);
        return adminRepo.save(newAdmin);
    }

    public boolean updateParkingCharge(Double newCharge, AdminUser adminUser){
        Optional<AdminUser> verifiedUser = adminRepo.findById(adminUser.getId());
        if(!verifiedUser.isPresent()) return false;
        if (!verifiedUser.get().getPassword().equalsIgnoreCase(adminUser.getPassword())) return false;
        parkingChargePerSecond = newCharge;
        return true;
    }

    public Double getParkingCharges(){
        return parkingChargePerSecond;
    }

    public List<ParkingSlot> updateOrAddParkingSlots(List<ParkingSlot> newParkingSlots){
        List<ParkingSlot> updatedSlots = slotRepo.saveAll(newParkingSlots);
        automationService.updateDisplayBoard();
        return updatedSlots;
    }

    public void removeParkingSlot(String slotId){
        slotRepo.deleteById(slotId);
        automationService.updateDisplayBoard();
    }


}
