package ParkingLotManagementSystem.models;

public abstract class Vehicle {
    VehicleType vehicleType;
    String regNumber;
    ParkingTicket parkingTicket;
    Vehicle(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
    void assignTicket(ParkingTicket parkingTicket) {
        this.parkingTicket = parkingTicket;
    }
}

