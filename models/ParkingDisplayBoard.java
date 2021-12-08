package ParkingLotManagementSystem.models;

public class ParkingDisplayBoard {
    ParkingSpot electricParkingSpot;
    ParkingSpot largeParkingSpot;
    ParkingSpot handicappedParkingSpot;

    public ParkingDisplayBoard() {
        this.electricParkingSpot = null;
        this.largeParkingSpot = null;
        this.handicappedParkingSpot = null;
    }

    ParkingSpot getElectricSpot() {
        return electricParkingSpot;
    }
    ParkingSpot getHandicappedSpot() {
        return handicappedParkingSpot;
    }
    ParkingSpot getLargeSpot() {
        return largeParkingSpot;
    }
    void setHandicappedSpot(ParkingSpot parkingSpot) {
        this.handicappedParkingSpot = parkingSpot;
    }

    void setLargeSpot(ParkingSpot parkingSpot) {
        this.largeParkingSpot = parkingSpot;
    }

    void setElectricSpot(ParkingSpot parkingSpot) {
        this.electricParkingSpot = parkingSpot;
    }

    void showEmptyParkingSpots() {
        if (electricParkingSpot == null) {
            System.out.println("No empty ElectricParkingSpot");
        } else {
            System.out.println("ElectricParkingSpot : " + electricParkingSpot.spotId);
        }
        if (largeParkingSpot == null) {
            System.out.println("No empty LargeParkingSpot");
        } else {
            System.out.println("LargeParkingSpot : " + largeParkingSpot.spotId);
        }
        if (handicappedParkingSpot == null) {
            System.out.println("No empty HandicappedParkingSpot");
        } else {
            System.out.println("HandicappedParkingSpot : " + handicappedParkingSpot.spotId);
        }
    }
}
