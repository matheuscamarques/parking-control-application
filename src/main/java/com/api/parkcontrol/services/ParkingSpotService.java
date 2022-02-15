package com.api.parkcontrol.services;

import javax.transaction.Transactional;

import com.api.parkcontrol.models.ParkingSpotModel;
import com.api.parkcontrol.repositories.IParkingSpotRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingSpotService {
    
    final IParkingSpotRepository parkingSpotRepository;
    public ParkingSpotService(IParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    @Transactional
    public ParkingSpotModel save(ParkingSpotModel parkingSpotModel) {
       return parkingSpotRepository.save(parkingSpotModel);
    }


}
