package ParkingLotManagementSystem.models;

import java.util.HashMap;

public class ParkingRate {
    HashMap<Long, Double> hourlyRate;

    public ParkingRate(HashMap<Long, Double> hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
