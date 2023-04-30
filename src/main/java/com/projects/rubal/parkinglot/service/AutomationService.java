package com.projects.rubal.parkinglot.service;

import com.projects.rubal.parkinglot.model.ParkingSlot;
import com.projects.rubal.parkinglot.repo.ParkingSlotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

@Service
public class AutomationService {

    @Autowired
    ParkingSlotRepository slotRepo;

    static final Logger log = LoggerFactory.getLogger(AdminService.class);
    private String displayBoardStatus = "FULL";

    public boolean openEntryGate(){
        return true;
    }

    public boolean openExitGate(){
        return true;
    }

    public String scanVehicle(){
        HashSet<String> myHashSet = new HashSet<>();
        myHashSet.add("ABCD1234");
        myHashSet.add("EFGH1234");
        myHashSet.add("IJKL1234");
        myHashSet.add("MNOP1234");
        myHashSet.add("QRST1234");
        myHashSet.add("UVWX1234");
        myHashSet.add("YZ123456");
        myHashSet.add("ABC67890");
        myHashSet.add("EFG67890");


        int size = myHashSet.size();
        int item = new Random().nextInt(size); // In real life, the Random object should be rather more shared than this
        int i = 0;
        for(String obj : myHashSet)
        {
            if (i == item)
                return obj;
            i++;
        }
        return null;
    }

    public void updateDisplayBoard(){
        List<ParkingSlot> openSlots = slotRepo.findByAvailability(true);
        if(openSlots != null && openSlots.size()>0) displayBoardStatus = "AVAILABLE";
        else displayBoardStatus = "FULL";
    }

    public String getDisplayBoardStatus(){
        return displayBoardStatus;
    }

}
