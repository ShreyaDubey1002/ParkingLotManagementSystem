package ParkingLotManagementSystem.models;

public abstract class ParkingSpot {
    String spotId;
    static int lastSpotId = 0;
    ParkingSpotType parkingSpotType;
    Vehicle vehicle;
    boolean isFree;

    ParkingSpot(ParkingSpotType parkingSpotType) {
        this.isFree = true;
        this.vehicle = null;
        this.lastSpotId = this.lastSpotId + 1;
        this.spotId = String.valueOf(lastSpotId);
        this.parkingSpotType = parkingSpotType;
        System.out.println("ParkingSpot added : " + this.spotId);
    }

    boolean isFree() {
        return isFree;
    }

    void assignVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        this.isFree = false;
    }

    void removeVehicle() {
        this.vehicle = null;
        this.isFree = true;
    }
}
