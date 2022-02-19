package com.api.parkcontrol.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import com.api.parkcontrol.dtos.ParkingSpotDTO;
import com.api.parkcontrol.models.ParkingSpotModel;
import com.api.parkcontrol.services.ParkingSpotService;


import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-spot")
public class ParkingController {
    final ParkingSpotService parkingSpotService;
    public ParkingController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(
            @RequestBody 
            @Valid 
            ParkingSpotDTO parkingSpotDTO
    ) {
        if(parkingSpotService.existsByLicensePlateCar(parkingSpotDTO.getLicensePlateCar())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Car Plate already registered");
        }
        if(parkingSpotService.existsByParkingSpotNumber(parkingSpotDTO.getParkingSpotNumber())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Parking spot already registered");
        }
        if(parkingSpotService.existsByApartamentAndBlock(parkingSpotDTO.getApartament(), parkingSpotDTO.getBlock())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Apartament/block already registered");
        }
        var parkingSpotModel = new ParkingSpotModel();
        BeanUtils
            .copyProperties(parkingSpotDTO, parkingSpotModel);
        
        parkingSpotModel
            .setRegistrationDate(
                LocalDateTime.now(
                    ZoneId.of("UTC")
                )
            );
            
        return ResponseEntity
             .status(HttpStatus.CREATED)
             .body(
                  parkingSpotService.save(parkingSpotModel)
             );

    }

    @GetMapping
    public ResponseEntity<List<ParkingSpotModel>> getAllParkingSpots() {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(
                parkingSpotService.findAll()
            );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getParkingSpotById(
        @PathVariable(value = "id") UUID id
    ) {
        Optional<ParkingSpotModel> parkingSpotModel = parkingSpotService.findById(id);
        if(!parkingSpotModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot Not Found");
        }

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(parkingSpotModel.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpotById(
        @PathVariable(value = "id") UUID id
    ) {
        Optional<ParkingSpotModel> parkingSpotModel = parkingSpotService.findById(id);
        if(!parkingSpotModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot Not Found");
        }

        parkingSpotService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Parking Spot Deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(
        @PathVariable(value = "id") UUID id,
        @RequestBody @Valid ParkingSpotDTO parkingSpotDTO
    ) {
        Optional<ParkingSpotModel> parkingSpotModel = parkingSpotService.findById(id);
        if(!parkingSpotModel.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Spot Not Found");
        }

        BeanUtils
            .copyProperties(parkingSpotDTO, parkingSpotModel.get());
        
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(parkingSpotService.save(parkingSpotModel.get()));
    }
}
