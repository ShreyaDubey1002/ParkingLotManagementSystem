package ParkingLotManagementSystem.models;

import java.util.HashMap;

public class ParkingFloor {
    String floorName;
    static int lastFloorId = 0;
    HashMap<String, ParkingSpot> handicappedParkingSpots;
    HashMap<String, ParkingSpot> largeParkingSpots;
    HashMap<String, ParkingSpot> electricParkingSpots;
    ParkingDisplayBoard parkingDisplayBoard;
    CustomerInfoPanel customerInfoPanel;

    public ParkingFloor() {
        this.lastFloorId = this.lastFloorId + 1;
        this.floorName = String.valueOf(lastFloorId);
        this.handicappedParkingSpots = new HashMap<String, ParkingSpot>();
        this.largeParkingSpots = new HashMap<String, ParkingSpot>();
        this.electricParkingSpots =  new HashMap<String, ParkingSpot>();
        this.parkingDisplayBoard = null;
        this.customerInfoPanel = null;
    }

    void addParkingDisplayBoard(ParkingDisplayBoard parkingDisplayBoard) {
        this.parkingDisplayBoard = parkingDisplayBoard;
    }

    void addCustomerInfoPanel(CustomerInfoPanel customerInfoPanel) {
        this.customerInfoPanel = customerInfoPanel;
    }

    void addParkingSpot(ParkingSpot parkingSpot) {
        switch(parkingSpot.parkingSpotType) {
            case LARGE: largeParkingSpots.put(parkingSpot.spotId, parkingSpot);
                        updateAndDisplayBoardForLargeParkingSpots(parkingSpot, true);
                        break;
            case HANDICAPPED: handicappedParkingSpots.put(parkingSpot.spotId, parkingSpot);
                              updateAndDisplayBoardForHandicappedParkingSpots(parkingSpot, true);
                              break;
            case ELECTRIC: electricParkingSpots.put(parkingSpot.spotId, parkingSpot);
                           updateAndDisplayBoardForElectricParkingSpots(parkingSpot, true);
                           break;
            default:
                System.out.println("Wrong Parking Slot type");
        }
    }

    void addVehicleToParkingSpot(Vehicle vehicle, ParkingSpot parkingSpot) {
        parkingSpot.assignVehicle(vehicle);
        switch (parkingSpot.parkingSpotType) {
            case HANDICAPPED: updateAndDisplayBoardForHandicappedParkingSpots(parkingSpot, false);
                          break;
            case LARGE: updateAndDisplayBoardForLargeParkingSpots(parkingSpot, false);
                        break;
            case ELECTRIC: updateAndDisplayBoardForElectricParkingSpots(parkingSpot, false);
                           break;
            default:
                System.out.println("Wrong Parking Slot type");
        }
    }

    void updateAndDisplayBoardForHandicappedParkingSpots(ParkingSpot parkingSpot, boolean isAdd) {
        if (isAdd) {
            if (parkingDisplayBoard.getHandicappedSpot() == null) {
                parkingDisplayBoard.setHandicappedSpot(parkingSpot);
            }
            parkingDisplayBoard.showEmptyParkingSpots();
            return;
        }
        if (parkingDisplayBoard.getHandicappedSpot().spotId == parkingSpot.spotId) {
            boolean flag = false;
            for (String spotId : handicappedParkingSpots.keySet()) {
                if (handicappedParkingSpots.get(spotId).isFree()) {
                    parkingDisplayBoard.setHandicappedSpot(handicappedParkingSpots.get(spotId));
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                parkingDisplayBoard.setHandicappedSpot(null);
            }
            parkingDisplayBoard.showEmptyParkingSpots();
        }
    }

    void updateAndDisplayBoardForLargeParkingSpots(ParkingSpot parkingSpot, boolean isAdd) {
        if (isAdd) {
            if (parkingDisplayBoard.getLargeSpot() == null) {
                parkingDisplayBoard.setLargeSpot(parkingSpot);
            }
            parkingDisplayBoard.showEmptyParkingSpots();
            return;
        }
        if (parkingDisplayBoard.getLargeSpot().spotId == parkingSpot.spotId) {
            boolean flag = false;
            for (String spotId : largeParkingSpots.keySet()) {
                if (largeParkingSpots.get(spotId).isFree()) {
                    parkingDisplayBoard.setLargeSpot(largeParkingSpots.get(spotId));
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                parkingDisplayBoard.setLargeSpot(null);
            }
            parkingDisplayBoard.showEmptyParkingSpots();
        }
    }

    void updateAndDisplayBoardForElectricParkingSpots(ParkingSpot parkingSpot, boolean isAdd) {
        if (isAdd) {
            if (parkingDisplayBoard.getElectricSpot() == null) {
                parkingDisplayBoard.setElectricSpot(parkingSpot);
            }
            parkingDisplayBoard.showEmptyParkingSpots();
            return;
        }
        if (parkingDisplayBoard.getElectricSpot().spotId == parkingSpot.spotId) {
            boolean flag = false;
            for (String spotId : electricParkingSpots.keySet()) {
                if (electricParkingSpots.get(spotId).isFree()) {
                    parkingDisplayBoard.setElectricSpot(electricParkingSpots.get(spotId));
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                parkingDisplayBoard.setElectricSpot(null);
            }
            parkingDisplayBoard.showEmptyParkingSpots();
        }
    }

    void freeParkingSpot(ParkingSpot parkingSpot) {
        parkingSpot.removeVehicle();
        switch (parkingSpot.parkingSpotType) {
            case HANDICAPPED: updateAndDisplayBoardForHandicappedParkingSpots(parkingSpot, true);
                break;
            case LARGE: updateAndDisplayBoardForLargeParkingSpots(parkingSpot, true);
                break;
            case ELECTRIC: updateAndDisplayBoardForElectricParkingSpots(parkingSpot, true);
                break;
            default:
                System.out.println("Wrong Parking Slot type");
        }
    }
}
