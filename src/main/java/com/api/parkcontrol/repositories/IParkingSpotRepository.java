package com.api.parkcontrol.repositories;

import java.util.UUID;
import com.api.parkcontrol.models.ParkingSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID> {
    boolean existsByLicensePlateCar(String licensePlateCar);
    boolean existsByParkingSpotNumber(String parkingSpotNumber);
    boolean existsByApartamentAndBlock(String apartament, String block);
}
    

