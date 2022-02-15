package com.api.parkcontrol.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.validation.Valid;

import com.api.parkcontrol.dtos.ParkingSpotDTO;
import com.api.parkcontrol.models.ParkingSpotModel;
import com.api.parkcontrol.services.ParkingSpotService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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
        // TODO  validar se ja existe licence plate car
        // TODO  validar se ja existe parking spot number
        // TODO  validar se ja existe apartamento 
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
}
