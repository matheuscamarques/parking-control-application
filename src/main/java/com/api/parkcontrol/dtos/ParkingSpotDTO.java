package com.api.parkcontrol.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ParkingSpotDTO {
    @NotBlank
    private String parkingSpotNumber;
    @NotBlank
    @Size(min = 7, max = 7)
    private String licensePlateCar;
    @NotBlank
    private String brandCar;
    @NotBlank
    private String modelCar;
    @NotBlank
    private String colorCar;
    @NotBlank
    private String responsibleName;
    @NotBlank
    private String apartament;
    @NotBlank
    private String block;

    public String getParkingSpotNumber() {
        return parkingSpotNumber;
    }

    public String getLicensePlateCar() {
        return licensePlateCar;
    }

    public String getBrandCar() {
        return brandCar;
    }

    public String getModelCar() {
        return modelCar;
    }

    public String getColorCar() {
        return colorCar;
    }


    public String getResponsibleName() {
        return responsibleName;
    }

    public String getApartament() {
        return apartament;
    }

    public String getBlock() {
        return block;
    }

    // Setters

    public void setParkingSpotNumber(String parkingSpotNumber) {
        this.parkingSpotNumber = parkingSpotNumber;
    }

    public void setLicensePlateCar(String licensePlateCar) {
        this.licensePlateCar = licensePlateCar;
    }

    public void setBrandCar(String brandCar) {
        this.brandCar = brandCar;
    }

    public void setModelCar(String modelCar) {
        this.modelCar = modelCar;
    }

    public void setColorCar(String colorCar) {
        this.colorCar = colorCar;
    }

    public void setResponsibleName(String responsibleName) {
        this.responsibleName = responsibleName;
    }

    public void setApartament(String apartament) {
        this.apartament = apartament;
    }

    public void setBlock(String block) {
        this.block = block;
    }
}
